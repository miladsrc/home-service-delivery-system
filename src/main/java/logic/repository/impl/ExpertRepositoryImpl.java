package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Admin;
import domain.Expert;
import logic.repository.ExpertRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ExpertRepositoryImpl extends BaseRepositoryImpl<Expert, Long> implements ExpertRepository {
    public ExpertRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Expert> getEntityClass() {
        return Expert.class;
    }


    @Override
    public boolean signIn(String phone, String password){
        String hql = "FROM Expert a " +
                "WHERE a.phoneNumber = :phone AND a.password = :password";

        Query<Admin> query = session.createQuery(hql, Admin.class)
                .setParameter("phone", phone)
                .setParameter("password", password);

        return query.uniqueResult() != null;
    }
}