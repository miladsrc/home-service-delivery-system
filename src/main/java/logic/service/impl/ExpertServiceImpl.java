package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.service.BaseServiceImpl;
import domain.Expert;
import domain.ExpertState;
import logic.repository.ExpertRepository;
import logic.service.ExpertService;
import util.ApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ExpertServiceImpl extends BaseServiceImpl<Expert, Long, ExpertRepository>
        implements ExpertService {

    public ExpertServiceImpl(ExpertRepository repository) {
        super(repository);
    }

    private final Scanner scanner = new Scanner(System.in);

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
    public boolean signIn(String phone, String password) {
        try {
            if (repository.signIn(phone, password)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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


    @Override
    public boolean confirmExpertWithAdmin() throws DatabaseOperationException {
        List<Expert> experts = ApplicationContext.getExpertService().findAll();

        List<Expert> newExperts = experts.stream()
                .filter(expert -> expert.getExpertiseState().equals(ExpertState.NEW))
                .toList();

        if (newExperts.isEmpty()) {
            System.out.println("No new experts available for confirmation.");
            return false;
        }

        System.out.println("List of new experts:");
        newExperts.forEach(expert -> System.out.println("ID: " + expert.getId() + ", Name: " + expert.getFirstName()));

        System.out.println("Please enter the ID of the expert you wish to confirm:");
        Long expertId = scanner.nextLong();

        Expert expertToConfirm = newExperts.stream()
                .filter(expert -> expert.getId().equals(expertId))
                .findFirst()
                .orElse(null);

        if (expertToConfirm == null) {
            System.out.println("No expert found with the entered ID.");
            return false;
        }

        expertToConfirm.setExpertiseState(ExpertState.CONFIRMED);
        ApplicationContext.getExpertService().saveOrUpdate(expertToConfirm);

        System.out.println("Expert successfully confirmed.");
        return true;
    }

    public byte[] requestPhotoFromExpert() {

        System.out.println("Please provide the path to the JPEG photo file (not more than 300 KB): ");
        String photoFilePath = scanner.next();
        scanner.nextLine();

        try {
            File photoFile = new File(photoFilePath);
            long fileSizeInKB = photoFile.length() / 1024;
            if (fileSizeInKB > 300) {
                throw new IOException("The provided photo is more than 300 KB in size.");
            } else if (!isJPEG(photoFile)) {
                throw new IOException("The provided photo is not in JPEG format.");
            }

            Path path = Paths.get(photoFilePath);
            byte[] photoData = Files.readAllBytes(path);
            return photoData;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private boolean isJPEG(File file) {
        boolean flag = false;
        try {
            BufferedImage image = ImageIO.read(file);
            flag = (image != null) && (file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void savePhotoToFile(byte[] photoData, String outputFilePath) {
        try {
            Path path = Paths.get(outputFilePath);
            Files.write(path, photoData);
            System.out.println("Photo saved successfully to: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error saving photo: " + e.getMessage());
        }
    }
}

