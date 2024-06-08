package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Expert;
import logic.repository.ExpertRepository;
import logic.service.ExpertService;

public class ExpertServiceImpl extends BaseServiceImpl<Expert, Long, ExpertRepository>
        implements ExpertService {

    public ExpertServiceImpl(ExpertRepository repository) {
        super(repository);
    }

    public Expert signUp(Expert expert) {
        // Validate first name and last name are not empty
        if (expert.getFirstName() == null || expert.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name cannot be empty.");
        }

        // Validate account balance (credit amount) is positive
        if (expert.getCreditAmount() < 0) {
            throw new IllegalArgumentException("Account balance must be positive.");
        }

        // Validate password meets criteria (at least 8 characters with a mix of uppercase, lowercase, and digits)
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!expert.getPassword().matches(passwordPattern)) {
            throw new IllegalArgumentException("Password must have at least 8 characters with a mix of uppercase, lowercase, and digits.");
        }

        // Validate email format using a regular expression
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!expert.getEmail().matches(emailPattern)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Validate image size (should be below 300 KB)
        if (expert.getImageData() != null && expert.getImageData().length > 300 * 1024) {
            throw new IllegalArgumentException("Image size must be below 300 KB.");
        }

        // Save or update the expert in the repository
        return repository.saveOrUpdate(expert);
    }
}