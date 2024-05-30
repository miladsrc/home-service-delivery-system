package logic.repository.impl;


import base.repository.BaseRepositoryImpl;
import domain.Admin;
import logic.repository.AdminRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.Random;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin, Long> implements AdminRepository {


    public AdminRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }
}
