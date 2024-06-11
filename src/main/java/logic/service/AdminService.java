package logic.service;

import base.service.BaseService;
import domain.Admin;
import domain.Expert;

import java.util.Optional;

public interface AdminService extends BaseService<Admin, Long> {

    boolean signIn(String phone, String password);

    Optional<Expert> confirmExpert();
}
