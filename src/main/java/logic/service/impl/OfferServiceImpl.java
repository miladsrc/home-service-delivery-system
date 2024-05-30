package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Offer;
import logic.repository.OfferRepository;
import logic.service.OfferService;

public class OfferServiceImpl extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {

    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }
}