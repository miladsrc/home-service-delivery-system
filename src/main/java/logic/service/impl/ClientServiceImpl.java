package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Client;
import logic.repository.ClientRepository;
import logic.service.ClientService;

public class ClientServiceImpl extends BaseServiceImpl<Client, Long, ClientRepository>
implements ClientService {
    public ClientServiceImpl(ClientRepository repository) {
        super(repository);
    }


    @Override
    public Client signUp(Client client) {
        // Validate first name and last name are not empty
        if (client.getFirstName() == null || client.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name cannot be empty.");
        }

        // Validate account balance (credit amount) is positive
        if (client.getCreditAmount() < 0) {
            throw new IllegalArgumentException("Account balance must be positive.");
        }

        // Validate password meets criteria (at least 8 characters with a mix of uppercase, lowercase, and digits)
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!client.getPassword().matches(passwordPattern)) {
            throw new IllegalArgumentException("Password must have at least 8 characters with a mix of uppercase, lowercase, and digits.");
        }

        // Validate email format using a regular expression
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!client.getEmail().matches(emailPattern)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Validate image size (should be below 300 KB)
        if (client.getImageData() != null && client.getImageData().length > 300 * 1024) {
            throw new IllegalArgumentException("Image size must be below 300 KB.");
        }

        // Save or update the client in the repository
        return repository.saveOrUpdate(client);
    }
}
