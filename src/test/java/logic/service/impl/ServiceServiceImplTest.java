package logic.service.impl;

import base.exception.NotFoundException;
import domain.Service;
import logic.service.ServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ServiceServiceImplTest {

    private ServiceService serviceService;


    @BeforeEach
    void setUp() {
        serviceService = ApplicationContext.getServiceService();
    }

    @Test
    void testCreateService() {
        Service service = Service.builder()
                .serviceName("My Service")
                .build();

        Service createdService = serviceService.create(service);
        assertNotNull(createdService);
    }

    @Test
    void testFindServiceById() throws NotFoundException {
        Long id = 1L;
        Service foundService = serviceService.findById(id);
        assertNotNull(foundService);
    }

    @Test
    void testUpdateService() {
        Service service = new Service(/* initialize service properties */);
        Service updatedService = serviceService.update(service);
        assertNotNull(updatedService);
    }

    @Test
    void testDeleteServiceById() {
        Long id = 1L;
        assertDoesNotThrow(() -> serviceService.deleteById(id));
    }
}
