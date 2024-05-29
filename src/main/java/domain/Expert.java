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
@Table(name = "expert")
public class Expert extends User {

    @Column(name = "credit_amount")
    @Positive(message = "Credit amount must be positive")
    @Max(value = 100000, message = "Credit amount cannot exceed 100000")
    double creditAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expertise_state")
    private ExpertiseState expertiseState;

    @ElementCollection
    @CollectionTable(name = "expert_address", joinColumns = @JoinColumn(name = "expert_id"))
    private List<Address> addresses;
}
