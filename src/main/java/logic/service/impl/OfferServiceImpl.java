package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import domain.Offer;
import logic.repository.OfferRepository;
import logic.service.OfferService;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;

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
    public Offer saveOrUpdate(Offer offer) throws DatabaseOperationException {
        try {
            beginTransaction();
            Offer savedOffer = super.saveOrUpdate(offer);
            commitTransaction();
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

    /**
     * Finds an Offer by its ID. Wraps the operation in a transaction.
     *
     * @param id The ID of the Offer to find.
     * @return The found Offer.
     * @throws NotFoundException if the Offer is not found.
     */
    @Override
    public Offer findById(Long id) throws NotFoundException, DatabaseOperationException {
        try {
            beginTransaction();
            Offer foundOffer = super.findById(id);
            commitTransaction();
            return foundOffer;
        } catch (NotFoundException e) {
            // Handle the case where the offer is not found
            rollbackTransaction();
            throw e;
        } catch (HibernateException e) {
            // Handle Hibernate exceptions
            rollbackTransaction();
            throw new DatabaseOperationException("Unable to find the offer with ID: " + id, e);
        } catch (Exception e) {
            // Handle other exceptions
            rollbackTransaction();
            throw new ServiceException("An unexpected error occurred while finding the offer", e);
        }
    }

    /**
     * Deletes an Offer. Wraps the operation in a transaction.
     *
     * @param offer The Offer to delete.
     */
    @Override
    public void delete(Offer offer) throws DatabaseOperationException {
        try {
            beginTransaction();
            super.delete(offer);
            commitTransaction();
        } catch (HibernateException e) {
            // Handle Hibernate exceptions
            rollbackTransaction();
            throw new DatabaseOperationException("Unable to delete the offer", e);
        } catch (Exception e) {
            // Handle other exceptions
            rollbackTransaction();
            throw new ServiceException("An unexpected error occurred while deleting the offer", e);
        }
    }
}