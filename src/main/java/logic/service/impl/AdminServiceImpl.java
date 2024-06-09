package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Admin;
import logic.repository.AdminRepository;
import logic.service.AdminService;

import java.util.Scanner;


public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository>
implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    private final Scanner scanner = new Scanner(System.in);


    @Override
    public boolean signIn(String phone, String password){
        try {
            beginTransaction();
            if(repository.signIn(phone, password)){
                return true;
            }
            commitTransaction();
        }catch (Exception e){
            e.getMessage();
            return false;
        }
        return false;
    }
}
