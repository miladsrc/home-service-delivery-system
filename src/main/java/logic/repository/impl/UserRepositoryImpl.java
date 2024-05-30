package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import base.service.BaseServiceImpl;
import domain.User;
import logic.repository.UserRepository;
import org.hibernate.Session;

public class UserRepositoryImpl extends BaseRepositoryImpl<User,Long>
        implements UserRepository {

    public UserRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
