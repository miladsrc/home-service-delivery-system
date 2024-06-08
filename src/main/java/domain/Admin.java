package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@SoftDelete
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "admin")
public class Admin extends User {

    private static final String GRADE_COLUMN = "admin_grade";

    @Column(name = GRADE_COLUMN, unique = true)
    private String grade;

    public Admin() {
    }
}
