package domain;


import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@SoftDelete
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User extends BaseEntity<Long> {

    @Column(name = "first_name")
    @NotNull
    String firstName;

    @Column(name = "last_name")
    @NotNull
    String lastName;

    @Column(name = "password", unique = true)
    @NotNull
    String password;

    @Column(name = "phone_number")
    @NotNull

    String phoneNumber;

    @Lob
    @Column(name = "image_data")
    byte[] imageData;

    @Column(columnDefinition = "DATE", name = "sign_up_date")
    LocalDate signUpDate;

}
