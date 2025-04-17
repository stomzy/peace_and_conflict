package com.example.pcr.dto.reponse;

import com.example.pcr.entity.ConflictCase;

import java.time.LocalDate;
import java.util.List;

public class ConflictCaseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private ConflictCase.CaseStatus status;
    private LocalDate dateReported;

    private UserResponseDTO reporter;
    private UserResponseDTO assignedMediator;

    private List<CaseNoteResponseDTO> notes;
    private List<ConflictPhaseResponseDTO> phases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConflictCase.CaseStatus getStatus() {
        return status;
    }

    public void setStatus(ConflictCase.CaseStatus status) {
        this.status = status;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }

    public void setDateReported(LocalDate dateReported) {
        this.dateReported = dateReported;
    }

    public UserResponseDTO getReporter() {
        return reporter;
    }

    public void setReporter(UserResponseDTO reporter) {
        this.reporter = reporter;
    }

    public UserResponseDTO getAssignedMediator() {
        return assignedMediator;
    }

    public void setAssignedMediator(UserResponseDTO assignedMediator) {
        this.assignedMediator = assignedMediator;
    }

    public List<CaseNoteResponseDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<CaseNoteResponseDTO> notes) {
        this.notes = notes;
    }

    public List<ConflictPhaseResponseDTO> getPhases() {
        return phases;
    }

    public void setPhases(List<ConflictPhaseResponseDTO> phases) {
        this.phases = phases;
    }
}
