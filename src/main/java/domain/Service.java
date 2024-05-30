package domain;

import base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@SoftDelete
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class Service extends BaseEntity<Long> {

    @Column(name = "service_name")
    String serviceName;

    @Column(name = "creation_time")
    LocalDateTime creationTime = LocalDateTime.now();

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    List<SubService> subServiceList = new ArrayList<>();


    //Helper method
    public void addSubServices(SubService subService){
        if (subService == null){
            subServiceList = new ArrayList<>();
        }
        subServiceList.add(subService);
        subService.setService(this);
    }

    public Service() {
        super();
    }
}
