package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Service;
import logic.repository.ServiceRepository;
import logic.service.ServiceService;

public class ServiceServiceImpl extends BaseServiceImpl<Service, Long, ServiceRepository>
        implements ServiceService {

    public ServiceServiceImpl(ServiceRepository repository) {
        super(repository);
    }


    /**
     * Creates a new service record in the database.
     *
     * @param service The service entity to be created.
     * @return The created service entity, or null
     */
    @Override
    public Service create(Service service) {
        try {
            beginTransaction();
            Service createdService = repository.saveOrUpdate(service);
            commitTransaction();
            return createdService;
        } catch (Exception e) {
            rollbackTransaction();
            throw new RuntimeException("Error creating service: " + e.getMessage(), e);
        }
    }


    /**
     * Finds a service by its ID.
     *
     * @param id The ID of the service to find.
     * @return The found service entity, or null if not found.
     */
    @Override
    public Service findById(Long id) {
        try {
            return repository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error finding service by ID: " + e.getMessage(), e);
        }
    }


    /**
     * Updates an existing service record in the database.
     *
     * @param service The service entity with updated properties.
     * @return The updated service entity.
     */
    @Override
    public Service update(Service service) {
        try {
            beginTransaction();
            Service updatedService = repository.saveOrUpdate(service);
            commitTransaction();
            return updatedService;
        } catch (Exception e) {
            rollbackTransaction();
            throw new RuntimeException("Error updating service: " + e.getMessage(), e);
        }
    }


    /**
     * Deletes a service by its ID.
     *
     * @param id The ID of the service to delete.
     */
    @Override
    public void deleteById(Long id) {
        try {
            // Begin a transaction
            beginTransaction();
            // Delete the service
            repository.deleteById(id);
            // Commit the transaction
            commitTransaction();
        } catch (Exception e) {
            // Handle any exceptions (e.g., database connection error)
            // Log the error or perform other error handling actions
            rollbackTransaction();
            throw new RuntimeException("Error deleting service by ID: " + e.getMessage(), e);
        }
    }
}