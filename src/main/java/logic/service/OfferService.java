package logic.service;

import base.exception.DatabaseOperationException;
import base.service.BaseService;
import domain.Offer;

public interface OfferService extends BaseService<Offer, Long> {
    Offer saveOrUpdateOffer(Offer offer) throws DatabaseOperationException;
}