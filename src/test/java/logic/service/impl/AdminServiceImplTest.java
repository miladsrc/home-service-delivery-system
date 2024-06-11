package logic.service.impl;

import domain.Admin;
import domain.Expert;
import domain.ExpertState;
import logic.service.AdminService;
import logic.service.ExpertService;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {



    @Test
    void testSignIn_Admin_Successful() {
        // Arrange
        AdminService adminService = ApplicationContext.getAdminService();
        Admin admin = Admin.builder()
                .firstName("abbas")
                .lastName("boazar")
                .password("SecurePass123")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .build();

        // Act
        boolean result = adminService.signIn(admin.getPhoneNumber(), admin.getPassword());

        // Assert
        assertTrue(result, () -> "Sign-in should be successful for valid credentials.");

    }

    @Test
    public void testConfirmExpert() {
        // Arrange
        ExpertService expertService = ApplicationContext.getExpertService();
        AdminService adminService = ApplicationContext.getAdminService();

        Expert expert = Expert.builder()
                .firstName("javad")
                .id(1L)
                .expertiseState(ExpertState.NEW)
                .build(); // Set the initial state

        // Act
        Optional<Expert> expert1 = adminService.confirmExpert();

        // Assert
        assertTrue(expert1.isPresent(), "Expert confirmation should succeed");
        assertEquals(ExpertState.CONFIRMED, expert.getExpertiseState(), "Expert state should be CONFIRMED");
    }

}