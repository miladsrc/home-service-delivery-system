package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.SubService;
import logic.repository.SubServiceRepository;
import org.hibernate.Session;

public class SubServiceRepositoryImpl extends BaseRepositoryImpl<SubService, Long> implements SubServiceRepository {
    public SubServiceRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<SubService> getEntityClass() {
        return SubService.class;
    }
}