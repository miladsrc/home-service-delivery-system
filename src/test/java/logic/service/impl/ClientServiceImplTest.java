package logic.service.impl;

import domain.Client;
import logic.service.ClientService;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {


    @Test
    void testSignUp() {
        Client client = Client.builder()
                .firstName("abbas")
                .lastName("sertain")
                .creditAmount(1000.0)
                .password("SecurePass123")
                .email("john.doe@example.com")
                .build();

        ClientService clientService = ApplicationContext.getClientService();

        Client savedClient = clientService.signUp(client);

        assertNotNull(savedClient);
        assertEquals("abbas", savedClient.getFirstName());
    }

}