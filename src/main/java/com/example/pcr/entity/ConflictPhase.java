package com.example.pcr.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "conflict_phases")
public class ConflictPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phaseName; // e.g., "Assessment", "Mediation", "Agreement"
    private String details;

    @Enumerated(EnumType.STRING)
    private PhaseStatus status;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private ConflictCase conflictCase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public PhaseStatus getStatus() {
        return status;
    }

    public void setStatus(PhaseStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public ConflictCase getConflictCase() {
        return conflictCase;
    }

    public void setConflictCase(ConflictCase conflictCase) {
        this.conflictCase = conflictCase;
    }

    public enum PhaseStatus {
        PENDING,
        ACTIVE,
        COMPLETED
    }
}
