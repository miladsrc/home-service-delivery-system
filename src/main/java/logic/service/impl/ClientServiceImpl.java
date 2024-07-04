package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import domain.Client;
import domain.Expert;
import domain.Offer;
import domain.Rate;
import logic.repository.ClientRepository;
import logic.service.ClientService;
import org.apache.bval.util.StringUtils;
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

public class ClientServiceImpl extends BaseServiceImpl<Client, Long, ClientRepository>
implements ClientService {

    Scanner scanner = new Scanner(System.in);
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


    @Override
    public boolean confirmOfferOfOrder(Long offerId) throws NotFoundException, DatabaseOperationException {

        ApplicationContext.getOrderService().gerListOfOrders();
        System.out.println("Enter order please:");
        Long input = scanner.nextLong();

        List<Offer> offers = ApplicationContext.getOfferService().getAllOffersForOrder(input);
        offers.forEach(offer -> System.out.println(offer.getId() + " : Price = " + offer.getPrice() + " description = " + offer.getDescription()));

        System.out.println("Enter id of offer to confirm:");
        Long offerId2 = scanner.nextLong();

        Optional<Offer> offerOptional = offers.stream()
                .filter(offering -> offering.getId().equals(offerId2))
                .findFirst();

        if (offerOptional.isPresent()){
            beginTransaction();
            Offer confirmedOffer = offerOptional.get();
            confirmedOffer.setConfirmed(true);
            ApplicationContext.getOfferService().saveOrUpdateOffer(confirmedOffer);
            commitTransaction();
            return true;
        }
        return false;
    }



    public boolean registerScoreForExpert(Long orderId) throws NotFoundException, DatabaseOperationException {

        System.out.println("Please choose a rating from the following options:");
        for (Rate rate : Rate.values()) {
            System.out.println(rate.ordinal() + " - " + rate);
        }

        System.out.println("Enter the number of your chosen rating:");
        int rateIndex = scanner.nextInt();

        Rate selectedRate;

        if (rateIndex >= 0 && rateIndex < Rate.values().length) {
            selectedRate = Rate.values()[rateIndex];
            System.out.println("You have selected: " + selectedRate);
        } else {
            System.out.println("Invalid number entered.");
            return false;
        }

        Optional<Offer> confirmedOffer = ApplicationContext.getOfferService().getAllOffersForOrder(orderId)
                .stream()
                .filter(Offer::isConfirmed)
                .findFirst();

        if (!confirmedOffer.isPresent()) {
            System.out.println("No confirmed offers found.");
            return false;
        }

        Expert expert = confirmedOffer.get().getExperts().get(0);

        int pastRate = expert.getRate().ordinal();
        int averageRate = (selectedRate.ordinal() + pastRate) / 2;

        beginTransaction();
        expert.setRate(Rate.values()[averageRate]);
        ApplicationContext.getExpertService().saveOrUpdate(expert);
        commitTransaction();

        return true;
    }




    public  byte[] requestPhotoFromClient() {

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

    private  boolean isJPEG(File file) {
        boolean flag = false;
        try {
            BufferedImage image = ImageIO.read(file);
            flag = (image != null) && (file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public  void savePhotoToFile(byte[] photoData, String outputFilePath) {
        try {
            Path path = Paths.get(outputFilePath);
            Files.write(path, photoData);
            System.out.println("Photo saved successfully to: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error saving photo: " + e.getMessage());
        }
    }

}
