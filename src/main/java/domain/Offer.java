package domain;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class Offer extends BaseEntity<Long> {


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    Order order;


    @OneToMany(mappedBy = "offers")
    List<Expert> experts;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "offer_price")
    double price;

    @CreationTimestamp
    @Column(name = "time")
    LocalDateTime time;


    //CONSTRUCTOR
    public Offer() {

    }
}
