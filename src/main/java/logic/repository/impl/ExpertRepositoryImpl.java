package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Expert;
import logic.repository.ExpertRepository;
import org.hibernate.Session;

public class ExpertRepositoryImpl extends BaseRepositoryImpl<Expert, Long> implements ExpertRepository {
    public ExpertRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Expert> getEntityClass() {
        return Expert.class;
    }
}