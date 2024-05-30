package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Client;
import logic.repository.ClientRepository;
import org.hibernate.Session;

public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, Long> implements ClientRepository {

    public ClientRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Client> getEntityClass() {
        return Client.class;
    }
}
