package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SoftDelete;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubService {
    @Id
    private Long id;

}
