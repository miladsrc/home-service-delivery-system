import domain.Admin;
import domain.User;
import util.ApplicationContext;
import util.SessionFactorySingleton;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Admin user = Admin.builder()
                .firstName("John")
                .lastName("Doe")
                .password("P@ssw0rd123")
                .phoneNumber("09123456789")
                .email("example@example.com")
                .imageData(new byte[]{/* image data */})
                .signUpDateTime(LocalDateTime.now())
                .build();

        ApplicationContext.getAdminService().saveOrUpdate(user);
    }
}