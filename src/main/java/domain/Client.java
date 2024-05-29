package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@SoftDelete
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Client extends User {

    @Column(name = "credit_amount")
    @Positive(message = "Credit amount must be positive")
    @Max(value = 100000, message = "Credit amount cannot exceed 100000")
    double creditAmount;

    @ElementCollection
    @CollectionTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"))
    private List<Address> addresses;
}
