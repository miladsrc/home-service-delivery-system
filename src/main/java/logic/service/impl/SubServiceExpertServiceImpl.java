package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import domain.SubService_Expert;
import logic.repository.SubServiceExpertRepository;
import logic.service.SubServiceExpertService;
import util.ApplicationContext;

import java.util.List;
import java.util.Scanner;

public class SubServiceExpertServiceImpl extends BaseServiceImpl<SubService_Expert, Long, SubServiceExpertRepository>
        implements SubServiceExpertService {

    public SubServiceExpertServiceImpl(SubServiceExpertRepository repository) {
        super(repository);
    }

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean addExpertToSubDuty() throws DatabaseOperationException, NotFoundException {
        List<SubService_Expert> subServiceExpertList = ApplicationContext.getSubServiceExpertService().findAll().stream()
                .filter(se -> !se.isConfirmed())
                .toList();


        System.out.println(
                "Enter id to confirm :"
        );

        subServiceExpertList.forEach(s -> System.out.println(s.getId()+"="+s.getSubService().getService()
                +" for  "+ s.getSubService()+"  -> "+ s.getExpert().getFirstName()));

        Long subExpertIdChoice = scanner.nextLong();

        SubService_Expert subServiceExpert = ApplicationContext.getSubServiceExpertService().findById(subExpertIdChoice);

        beginTransaction();
        subServiceExpert.setConfirmed(true);
        ApplicationContext.getSubServiceExpertService().saveOrUpdate(subServiceExpert);
        commitTransaction();

        return true;
    }


    @Override
    public boolean deleteExpertFromSubService() throws NotFoundException, DatabaseOperationException {

        List<SubService_Expert> subServiceExpertList = ApplicationContext.getSubServiceExpertService().findAll();

        if (subServiceExpertList.isEmpty()) {
            System.out.println("There are no experts to delete from sub-services.");
            return false;
        }

        System.out.println("Enter the ID of the expert you wish to delete from the sub-service:");
        subServiceExpertList.forEach(s -> System.out.println("ID: " + s.getExpert().getId() + " - Expert: " + s.getExpert().getFirstName() + " " + s.getExpert().getLastName()));

        Long expertId = scanner.nextLong();

        SubService_Expert subServiceExpertToDelete = subServiceExpertList.stream()
                .filter(s -> s.getExpert().getId().equals(expertId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Expert not found with ID: " + expertId)); // Handle the case where the expert is not found

        beginTransaction();
        ApplicationContext.getSubServiceExpertService().delete(subServiceExpertToDelete);
        commitTransaction();

        System.out.println("Expert successfully deleted from the sub-service.");
        return true;
    }
}