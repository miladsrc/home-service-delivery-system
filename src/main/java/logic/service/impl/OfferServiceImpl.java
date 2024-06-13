package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import domain.Offer;
import logic.repository.OfferRepository;
import logic.service.OfferService;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;
import util.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class OfferServiceImpl extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {

    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
    }


    /**
     * Saves or updates an Offer. Wraps the operation in a transaction.
     *
     * @param offer The Offer to save or update.
     * @return The saved or updated Offer.
     */
    @Override
    public Offer saveOrUpdateOffer(Offer offer) throws DatabaseOperationException {
        try {
            Offer savedOffer = ApplicationContext.getOfferService().saveOrUpdate(offer);
            return savedOffer;
        } catch (HibernateException e) {
            rollbackTransaction();
            throw new DatabaseOperationException("Unable to save or update the offer", e);
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("An unexpected error occurred while saving or updating the offer", e);
        }
    }


    @Override
    public List<Offer> getAllOffersForOrder(Long orderId) throws NotFoundException {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        List<Offer> offers = ApplicationContext.getOfferService().findAll();

        List<Offer> filteredOffers = offers.stream()
                .filter(offering -> offering.getOrder() != null && offering.getOrder().getId().equals(orderId))
                .collect(Collectors.toList());

        if (filteredOffers.isEmpty()) {
            throw new NotFoundException("No offers found for Order with id " + orderId);
        }

        return filteredOffers;
    }


}