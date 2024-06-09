package logic.service.impl;

import domain.Admin;
import logic.service.AdminService;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

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
        assertTrue(result, ()->"Sign-in should be successful for valid credentials.");

    }

}