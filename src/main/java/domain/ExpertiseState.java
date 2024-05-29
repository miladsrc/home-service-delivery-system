package domain;
import jakarta.persistence.Embeddable;

@Embeddable
public enum ExpertiseState {
    NEW,
    WAITING,
    ACCEPTED
}
