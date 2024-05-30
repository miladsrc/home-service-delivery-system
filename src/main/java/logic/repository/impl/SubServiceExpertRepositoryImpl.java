package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.SubService_Expert;
import logic.repository.SubServiceExpertRepository;
import org.hibernate.Session;

public class SubServiceExpertRepositoryImpl extends BaseRepositoryImpl<SubService_Expert, Long> implements SubServiceExpertRepository {
    public SubServiceExpertRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<SubService_Expert> getEntityClass() {
        return SubService_Expert.class;
    }
}