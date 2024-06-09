package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Client;
import logic.repository.ClientRepository;
import logic.service.ClientService;
import org.apache.bval.util.StringUtils;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl extends BaseServiceImpl<Client, Long, ClientRepository>
implements ClientService {
    public ClientServiceImpl(ClientRepository repository) {
        super(repository);
    }


    @Override
    public Client signUp(Client client) {
        try {
            if (StringUtils.isBlank(client.getFirstName()) || StringUtils.isBlank(client.getLastName()))  {
                throw new IllegalArgumentException("First name and last name cannot be empty.");
            }

            if (client.getCreditAmount() < 0) {
                throw new IllegalArgumentException("Account balance must be positive.");
            }

            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
            if (!client.getPassword().matches(passwordPattern)) {
                throw new IllegalArgumentException("Password must have at least 8 characters with a mix of uppercase, lowercase, and digits.");
            }

            String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (!client.getEmail().matches(emailPattern)) {
                throw new IllegalArgumentException("Invalid email format.");
            }

            if (client.getImageData() != null && client.getImageData().length > 300 * 1024) {
                throw new IllegalArgumentException("Image size must be below 300 KB.");
            }

            return repository.saveOrUpdate(client);
        } catch (Exception e) {
            e.getMessage();
            return client;
        }
    }


    @Override
    public boolean signIn(String phone, String password){
        try {
            if(repository.signIn(phone, password)){
                return true;
            }
        }catch (Exception e){
            e.getMessage();
            return false;
        }
        return false;
    }


    @Override
    public boolean changeClientPassword(String phone, String password, String oldPassword, String newPassword) {
        List<Client> clients = repository.findAll();
        Optional<Client> foundClient = clients.stream()
                .filter(client -> client.getPassword().equals(password) && client.getPhoneNumber().equals(phone))
                .findFirst();

        if (foundClient.isPresent() && foundClient.get().getPassword().equals(oldPassword)) {
            Client client = foundClient.get();
            client.setPassword(newPassword);
            beginTransaction();
            repository.saveOrUpdate(client);
            commitTransaction();
            return true;
        } else {
            rollbackTransaction();
            return false;
        }
    }



}
