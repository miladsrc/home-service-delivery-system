package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.SubService;
import logic.repository.SubServiceRepository;
import logic.service.SubServiceService;

public class SubServiceServiceImpl extends BaseServiceImpl<SubService, Long, SubServiceRepository>
        implements SubServiceService {

    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }



    /**
     * Creates a new sub-service record in the database.
     *
     * @param subService The sub-service entity to be created.
     * @return The created sub-service entity.
     */
    @Override
    public SubService create(SubService subService) {
        try {
            // Begin a transaction
            beginTransaction();

            // Save the sub-service entity to the database
            SubService createdSubService = repository.saveOrUpdate(subService);

            // Commit the transaction
            commitTransaction();

            return createdSubService;
        } catch (Exception e) {
            // Handle any exceptions (e.g., unique constraint violation)
            // Log the error or perform other error handling actions
            rollbackTransaction();
            throw new RuntimeException("Error creating sub-service: " + e.getMessage(), e);
        }
    }

    /**
     * Finds a sub-service by its ID.
     *
     * @param id The ID of the sub-service to find.
     * @return The found sub-service entity, or null if not found.
     */
    @Override
    public SubService findById(Long id) {
        try {
            // Search for the sub-service by ID
            return repository.findById(id).orElse(null);
        } catch (Exception e) {
            // Handle any exceptions (e.g., database connection error)
            // Log the error or perform other error handling actions
            throw new RuntimeException("Error finding sub-service by ID: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing sub-service record in the database.
     *
     * @param subService The sub-service entity with updated properties.
     * @return The updated sub-service entity.
     */
    @Override
    public SubService update(SubService subService) {
        try {
            // Begin a transaction
            beginTransaction();

            // Update the sub-service entity in the database
            SubService updatedSubService = repository.saveOrUpdate(subService);

            // Commit the transaction
            commitTransaction();

            return updatedSubService;
        } catch (Exception e) {
            // Handle any exceptions (e.g., unique constraint violation)
            // Log the error or perform other error handling actions
            rollbackTransaction();
            throw new RuntimeException("Error updating sub-service: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a sub-service by its ID.
     *
     * @param id The ID of the sub-service to delete.
     */
    @Override
    public void deleteById(Long id) {
        try {
            // Begin a transaction
            beginTransaction();

            // Delete the sub-service
            repository.deleteById(id);

            // Commit the transaction
            commitTransaction();
        } catch (Exception e) {
            // Handle any exceptions (e.g., database connection error)
            // Log the error or perform other error handling actions
            rollbackTransaction();
            throw new RuntimeException("Error deleting sub-service by ID: " + e.getMessage(), e);
        }
    }
}