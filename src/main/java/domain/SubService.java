package domain;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@SoftDelete
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class SubService extends BaseEntity<Long> {

    @Column(name = "sub_service_name")
    String subServiceName;

    @Column(name = "creation_time")
    LocalDateTime creationTime = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    Service service;

    @OneToMany(mappedBy = "subService", cascade = CascadeType.ALL)
    Set<SubService_Expert> subServiceExperts;

    @OneToOne(mappedBy = "subService")
    Order order;

    public SubService() {
        super();
    }
}
