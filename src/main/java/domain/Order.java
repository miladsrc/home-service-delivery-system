package domain;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@SoftDelete
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Long> {


    @OneToMany(mappedBy = "order")
    List<Offer> offer = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_service_id")
    SubService subService;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_id")
    Expert expert;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    Client client;

    @Enumerated(EnumType.ORDINAL)
    TaskState taskState;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "order_price")
    double price;

    @CreationTimestamp
    @Column(name = "time")
    LocalDateTime time;

    @Enumerated(EnumType.ORDINAL)
    Rate rate;

    public Order() {

    }
}
