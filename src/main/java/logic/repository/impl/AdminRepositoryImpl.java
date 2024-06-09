package logic.repository.impl;


import base.repository.BaseRepositoryImpl;
import domain.Admin;
import logic.repository.AdminRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin, Long> implements AdminRepository {

    public AdminRepositoryImpl(Session session) {
        super(session);
    }
    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }

    @Override
    public boolean signIn(String phone, String password){
        String hql = "FROM Admin a " +
                "WHERE a.phoneNumber = :phone AND a.password = :password";

        Query<Admin> query = session.createQuery(hql, Admin.class)
                .setParameter("phone", phone)
                .setParameter("password", password);

        return query.uniqueResult() != null;
    }


}
