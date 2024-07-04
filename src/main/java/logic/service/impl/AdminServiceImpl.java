package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.service.BaseServiceImpl;
import domain.Admin;
import domain.Expert;
import domain.ExpertState;
import logic.repository.AdminRepository;
import logic.service.AdminService;
import util.ApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    private final Scanner scanner = new Scanner(System.in);


    @Override
    public boolean signIn(String phone, String password) {
        try {
            beginTransaction();
            if (repository.signIn(phone, password)) {
                return true;
            }
            commitTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }


    @Override
    public Optional<Expert> confirmExpert() {
        List<Expert> expertList = ApplicationContext.getExpertService().findAll();

        expertList.forEach(expert -> System.out.println(expert.getId() + " - " + expert.getFirstName() + " " + expert.getLastName() + " " + expert.getExpertiseState()));
        System.out.println("Enter expert id to confirm statement:\n");
        Long choice = scanner.nextLong();

        boolean idExists = expertList.stream()
                .anyMatch(expert -> expert.getId().equals(choice));

        if (idExists) {
            Expert expert = expertList.stream()
                    .filter(experts -> experts.getId().equals(choice))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Expert not found!"));

            try {
                beginTransaction();
                if (expert == null) {
                    System.out.println("Expert not found!");
                    rollbackTransaction();
                    return Optional.empty();
                }
                expert.setExpertiseState(ExpertState.CONFIRMED);
                Optional<Expert> optionalExpert = Optional.ofNullable(ApplicationContext.getExpertService().saveOrUpdate(expert));
                commitTransaction();
                return optionalExpert;
            } catch (Exception e) {
                rollbackTransaction();
                throw new RuntimeException("Error confirming expert: " + e.getMessage(), e);
            } catch (DatabaseOperationException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Invalid Expert id!");
        return Optional.empty();
    }
}
