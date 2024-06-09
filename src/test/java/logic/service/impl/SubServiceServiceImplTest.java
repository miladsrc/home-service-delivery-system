package logic.service.impl;

import base.exception.NotFoundException;
import domain.SubService;
import logic.service.SubServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class SubServiceServiceImplTest {


    private SubServiceService subServiceService;

    @BeforeEach
    void setUp() {
        subServiceService = ApplicationContext.getSubServiceService();
    }

    @Test
    void testCreateSubService() {
        SubService subService = new SubService(/* initialize sub-service properties */);
        SubService createdSubService = subServiceService.create(subService);
        assertNotNull(createdSubService);
    }

    @Test
    void testFindSubServiceById() throws NotFoundException {
        Long id = 1L;
        SubService foundSubService = subServiceService.findById(id);
        assertNotNull(foundSubService);
    }

    @Test
    void testUpdateSubService() {
        SubService subService = new SubService(/* initialize sub-service properties */);
        SubService updatedSubService = subServiceService.update(subService);
        assertNotNull(updatedSubService);
    }

    @Test
    void testDeleteSubServiceById() {
        Long id = 1L;
        assertDoesNotThrow(() -> subServiceService.deleteById(id));
    }

}