package domain;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@SoftDelete
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Long> {


    @OneToOne(mappedBy = "order")
    Offer offer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subservice_id")
    SubService subService;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_id")
    Expert expert;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    Client client;

    @Enumerated
    TaskState taskState;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    String descripthion;

    @Column(name = "order_price")
    double price;

    @CreationTimestamp
    @Column(name = "time")
    LocalDateTime time;

    public Order() {

    }
}
