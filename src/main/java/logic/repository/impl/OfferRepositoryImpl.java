package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Offer;
import logic.repository.OfferRepository;
import org.hibernate.Session;

public class OfferRepositoryImpl extends BaseRepositoryImpl<Offer, Long> implements OfferRepository {
    public OfferRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Offer> getEntityClass() {
        return Offer.class;
    }
}