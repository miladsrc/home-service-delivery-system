package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Expert;
import logic.repository.ExpertRepository;
import logic.service.ExpertService;

import java.util.List;
import java.util.Optional;

public class ExpertServiceImpl extends BaseServiceImpl<Expert, Long, ExpertRepository>
        implements ExpertService {

    public ExpertServiceImpl(ExpertRepository repository) {
        super(repository);
    }


    @Override
    public Expert signUp(Expert expert) {
        if (expert.getFirstName() == null || expert.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name cannot be empty.");
        }

        if (expert.getCreditAmount() < 0) {
            throw new IllegalArgumentException("Account balance must be positive.");
        }

        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!expert.getPassword().matches(passwordPattern)) {
            throw new IllegalArgumentException("Password must have at least 8 characters with a mix of uppercase, lowercase, and digits.");
        }

        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!expert.getEmail().matches(emailPattern)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        if (expert.getImageData() != null && expert.getImageData().length > 300 * 1024) {
            throw new IllegalArgumentException("Image size must be below 300 KB.");
        }

        return repository.saveOrUpdate(expert);
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
    public boolean changeExpertPassword(String phone, String password, String oldPassword, String newPassword) {
        List<Expert> experts = repository.findAll();
        Optional<Expert> foundClient = experts.stream()
                .filter(expert -> expert.getPassword().equals(password) && expert.getPhoneNumber().equals(phone))
                .findFirst();

        if (foundClient.isPresent() && foundClient.get().getPassword().equals(oldPassword)) {
            Expert expert = foundClient.get();
            expert.setPassword(newPassword);
            beginTransaction();
            repository.saveOrUpdate(expert);
            commitTransaction();
            return true;
        } else {
            rollbackTransaction();
            return false;
        }
    }
}