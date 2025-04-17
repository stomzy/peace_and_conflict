package com.example.pcr.dto.request;

import com.example.pcr.entity.ConflictPhase;

public class ConflictPhaseRequestDTO {
    private Long caseId;
    private String phaseName;
    private String details;
    private ConflictPhase.PhaseStatus status;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
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
}
