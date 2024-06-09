package logic.service.impl;

import domain.Client;
import domain.Expert;
import logic.service.ClientService;
import logic.service.ExpertService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import util.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpertServiceImplTest {
    @Test
    void testSignUp() {
        Expert expert = Expert.builder()
                .firstName("abbas")
                .lastName("safari")
                .creditAmount(1000.0)
                .password("SecurePass123")
                .email("john.doe@example.com")
                .build();

        ExpertService expertService = ApplicationContext.getExpertService();

        Expert savedExpert = expertService.signUp(expert);

        assertNotNull(savedExpert);
        assertEquals("abbas", savedExpert.getFirstName(),
                () -> expert.getFirstName()+" not saved !" );
    }


    @Test
    void testSignIn_Expert_Successful() {
        // Arrange
        ExpertService expertService = ApplicationContext.getExpertService();
        Expert expert = Expert.builder()
                .firstName("abbas")
                .lastName("boar")
                .password("123")
                .phoneNumber("123")
                .email("john.doe@example.com")
                .build();

        // Act
        boolean result = expertService.signIn(expert.getPhoneNumber(), expert.getPassword());

        // Assert
        assertTrue(result, ()->"Sign-in should be successful for valid credentials.");

    }



    @Test
    void testChangeExpertPassword_Successful() {
        // Arrange
        ExpertService expertService = ApplicationContext.getExpertService();
        Expert expert = Expert.builder()
                .firstName("abbas")
                .lastName("boar")
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
        boolean result = expertService.changeExpertPassword(
                expert.getPhoneNumber(),
                expert.getPassword(),
                "oldPassword",
                "newPassword");

        // Assert
        assertTrue(result, "Password change should be successful.");
    }
}




