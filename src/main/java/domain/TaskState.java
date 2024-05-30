package domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum TaskState {
    AWAITING_EXPERT_PROPOSAL,
    AWAITING_EXPERT_SELECTION,
    AWAITING_EXPERT_ARRIVAL,
    STARTED,
    COMPLETED,
    PAYMENT_RECEIVED
}
