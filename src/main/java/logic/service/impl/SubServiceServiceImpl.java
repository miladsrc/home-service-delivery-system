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
            beginTransaction();
            SubService createdSubService = repository.saveOrUpdate(subService);
            commitTransaction();
            return createdSubService;
        } catch (Exception e) {
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
            return repository.findById(id).orElse(null);
        } catch (Exception e) {
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
            beginTransaction();
            SubService updatedSubService = repository.saveOrUpdate(subService);
            commitTransaction();
            return updatedSubService;
        } catch (Exception e) {
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
            beginTransaction();
            repository.deleteById(id);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw new RuntimeException("Error deleting sub-service by ID: " + e.getMessage(), e);
        }
    }
}