package domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public enum Rate {
    VERY_BAD,
    BAD,
    NORMAL,
    GOOD,
    VERY_GOOD,
    PERFECT,
    PROFESSIONAL;

}
