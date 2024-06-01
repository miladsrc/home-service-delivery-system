package domain;


import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@SuperBuilder
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
//@Table(name = "users")
public class User extends BaseEntity<Long> {

    static final String FIRST_NAME_COLUMN = "first_name";
    static final String LAST_NAME_COLUMN = "last_name";
    static final String PASSWORD_COLUMN = "password";
    static final String PHONE_NUMBER_COLUMN = "phone_number";
    static final String EMAIL_COLUMN = "email";
    static final String IMAGE_DATA_COLUMN = "image_data";
    static final String SIGN_UP_DATE_COLUMN = "sign_up_date";

    @Column(name = FIRST_NAME_COLUMN)
    @NotBlank
    @Length(min = 2, max = 50)
    String firstName;

    @Column(name = LAST_NAME_COLUMN)
    @NotBlank
    @Length(min = 2, max = 50)
    String lastName;

    @Column(name = PASSWORD_COLUMN, unique = true)
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Pattern must contain lowercase and uppercase letters, a number, and a special character")
    String password;

    @Column(name = PHONE_NUMBER_COLUMN)
    @NotBlank
    @Pattern(regexp = "^0[0-9]{2,}[0-9]{7,}$")
    String phoneNumber;

    @Email(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")
    String email;

    @Lob
    @Column(name = IMAGE_DATA_COLUMN, length = 300000)
    byte[] imageData;

    @Column(columnDefinition = "TIMESTAMP", name = SIGN_UP_DATE_COLUMN)
    LocalDateTime signUpDateTime;


    public User() {
        // Constructor logic (if needed)
    }
}
