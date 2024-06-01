package domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Rate {
    VERY_BAD,
    BAD,
    NORMAL,
    GOOD,
    VERY_GOOD,
    PERFECT,
    PROFESSIONAL
}
