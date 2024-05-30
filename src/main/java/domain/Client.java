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
@SuperBuilder
@SoftDelete
@ToString(callSuper = true)
@Entity
@Table(name = "client")
public class Client extends User {

    private static final double MAX_CREDIT_AMOUNT = 100000;
    private static final String DEFAULT_ADDRESS = "Unknown";

    @Column(name = "credit_amount")
    @Positive(message = "Credit amount must be positive")
    @Max(value = (long)MAX_CREDIT_AMOUNT, message = "Credit amount cannot exceed " + MAX_CREDIT_AMOUNT)
    double creditAmount;

    @ElementCollection
    @CollectionTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"))
    List<Address> addresses;

    @OneToOne(mappedBy = "client")
    Order order;

    public Client() {
        // Constructor logic (if needed)
    }
}
