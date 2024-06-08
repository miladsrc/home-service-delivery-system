package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SoftDelete
@FieldDefaults(level = AccessLevel.PACKAGE)
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "expert")
public class Expert extends User {

    private static final String DEFAULT_ADDRESS = "Unknown";

    @Column(name = "credit_amount")
    double creditAmount;

    @Column(name = "expertise_state_enable")
    ExpertState expertiseState = ExpertState.NEW;

    @ElementCollection
    @CollectionTable(name = "expert_address", joinColumns = @JoinColumn(name = "expert_id"))
    List<Address> addresses;

    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL)
    private Set<SubService_Expert> subServiceExperts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_id")
    Offer offers;

    @OneToOne(mappedBy = "expert")
    Order order;

    public Expert() {
    }
}

