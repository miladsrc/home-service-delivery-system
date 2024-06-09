package logic.service.impl;

import domain.Client;
import logic.service.ClientService;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {


    @Test
    void testSignUp_Successful() {
        // Arrange
        Client client = Client.builder()
                .firstName("abbas")
                .lastName("syllabi")
                .creditAmount(1000.0)
                .password("SecurePs123")
                .email("john.doe@example.com")
                .build();

        ClientService clientService = ApplicationContext.getClientService();

        // Act
        Client savedClient = clientService.signUp(client);

        // Assert
        assertNotNull(savedClient, "Client should be saved successfully.");
        assertEquals("abbas", savedClient.getFirstName(),
                "First name should match the expected value.");
    }


    @Test
    void testSignIn_Client_Successful() {
        // Arrange
        ClientService clientService = ApplicationContext.getClientService();
        Client client = Client.builder()
                .firstName("abbas")
                .lastName("boar")
                .password("123")
                .phoneNumber("123")
                .email("john.doe@example.com")
                .build();

        // Act
        boolean result = clientService.signIn(client.getPhoneNumber(), client.getPassword());

        // Assert
        assertTrue(result, "Sign-in should be successful for valid credentials.");

    }

    @Test
    void testChangeClientPassword_Successful() {
        // Arrange
        ClientService clientService = ApplicationContext.getClientService();
        Client client = Client.builder()
                .firstName("abbas")
                .lastName("boarBand")
                .password("oldPassword")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .build();

        // Act
        /* When testing this method,
         make sure that the new password
         has not been stored in the database
          in place of the old password!
         */
        boolean result = clientService.changeClientPassword(
                client.getPhoneNumber(),
                client.getPassword(),
                "oldPassword",
                "newPassword");

        // Assert
        assertTrue(result, "Password change should be successful.");
    }



}
