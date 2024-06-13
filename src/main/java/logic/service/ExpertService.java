package logic.service;

import base.exception.DatabaseOperationException;
import base.service.BaseService;
import domain.Expert;

public interface ExpertService extends BaseService<Expert, Long> {
    Expert signUp(Expert expert);

    boolean signIn(String phone, String password);

    boolean changeExpertPassword(String phone, String password, String oldPassword, String newPassword);

    boolean confirmExpertWithAdmin() throws DatabaseOperationException;
}