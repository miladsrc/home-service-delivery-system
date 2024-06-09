package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Admin;
import domain.Client;
import logic.repository.ClientRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, Long> implements ClientRepository {

    public ClientRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Client> getEntityClass() {
        return Client.class;
    }

    @Override
    public boolean signIn(String phone, String password){
        String hql = "FROM Client a " +
                "WHERE a.phoneNumber = :phone AND a.password = :password";

        Query<Client> query = session.createQuery(hql, Client.class)
                .setParameter("phone", phone)
                .setParameter("password", password);

        return query.uniqueResult() != null;
    }
}
