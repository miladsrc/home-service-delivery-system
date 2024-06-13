package domain;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubService_Expert extends BaseEntity<Long> {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_service_id")
    private SubService subService;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @Column(name = "creation_time")
    LocalDateTime creationTime = LocalDateTime.now();

    @Column(name = "isConfirmed")
    boolean isConfirmed = false;

    //TODO attention almost eager fetch type will cause n+1 query problem !
    /**native => List<SubService_Expert> subServiceExperts = entityManager.createQuery(
            "select se from SubService_Expert se join fetch se.subService ss join fetch se.expert e",
            SubService_Expert.class
    ).getResultList();*/
}
