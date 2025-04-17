package com.example.pcr.dto.request;

public class ConflictCaseRequestDTO {
    private String title;
    private String description;
    private String location;
    private Long reporterId;
    private Long assignedMediatorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getAssignedMediatorId() {
        return assignedMediatorId;
    }

    public void setAssignedMediatorId(Long assignedMediatorId) {
        this.assignedMediatorId = assignedMediatorId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }
}
