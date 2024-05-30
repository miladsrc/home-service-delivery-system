package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Admin;
import logic.repository.AdminRepository;
import logic.service.AdminService;


public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository>
implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }
}
