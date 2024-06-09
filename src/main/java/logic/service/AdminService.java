package logic.service;

import base.service.BaseService;
import domain.Admin;

public interface AdminService extends BaseService<Admin, Long> {

    boolean signIn(String phone, String password);
}
