package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Service;
import logic.repository.ServiceRepository;
import org.hibernate.Session;

public class ServiceRepositoryImpl extends BaseRepositoryImpl<Service, Long> implements ServiceRepository {
    public ServiceRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Service> getEntityClass() {
        return Service.class;
    }
}