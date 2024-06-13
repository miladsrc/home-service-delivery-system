package logic.service;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseService;
import domain.Offer;

import java.util.List;

public interface OfferService extends BaseService<Offer, Long> {
    Offer saveOrUpdateOffer(Offer offer) throws DatabaseOperationException;

    List<Offer> getAllOffersForOrder(Long orderId) throws NotFoundException;
}