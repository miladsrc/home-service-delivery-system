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

public class OfferServiceImpl extends BaseServiceImpl<Offer, Long, OfferRepository>
        implements OfferService {

    OfferService offerService = ApplicationContext.getOfferService();
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
            // Handle Hibernate exceptions, typically related to database connectivity or query issues
            rollbackTransaction();
            throw new DatabaseOperationException("Unable to save or update the offer", e);
        } catch (Exception e) {
            // Handle other exceptions, which could be related to business logic
            rollbackTransaction();
            throw new ServiceException("An unexpected error occurred while saving or updating the offer", e);
        }
    }

}