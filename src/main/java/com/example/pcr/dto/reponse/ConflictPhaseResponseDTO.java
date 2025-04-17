package com.example.pcr.dto.reponse;

import com.example.pcr.entity.ConflictPhase;

import java.time.LocalDateTime;

public class ConflictPhaseResponseDTO {
    private Long id;
    private String phaseName;
    private String details;
    private ConflictPhase.PhaseStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

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

    public ConflictPhase.PhaseStatus getStatus() {
        return status;
    }

    public void setStatus(ConflictPhase.PhaseStatus status) {
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
}
