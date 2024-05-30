package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.User;
import logic.repository.UserRepository;
import logic.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
