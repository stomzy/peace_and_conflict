package com.example.pcr.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "conflict_cases")
public class ConflictCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;

    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    private LocalDate dateReported;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "assigned_mediator_id")
    private User assignedMediator;

    @OneToMany(mappedBy = "conflictCase", cascade = CascadeType.ALL)
    private List<CaseNote> notes;

    @OneToMany(mappedBy = "conflictCase", cascade = CascadeType.ALL)
    private List<ConflictPhase> phases;

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

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }

    public void setDateReported(LocalDate dateReported) {
        this.dateReported = dateReported;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssignedMediator() {
        return assignedMediator;
    }

    public void setAssignedMediator(User assignedMediator) {
        this.assignedMediator = assignedMediator;
    }

    public List<CaseNote> getNotes() {
        return notes;
    }

    public void setNotes(List<CaseNote> notes) {
        this.notes = notes;
    }

    public List<ConflictPhase> getPhases() {
        return phases;
    }

    public void setPhases(List<ConflictPhase> phases) {
        this.phases = phases;
    }

    public enum CaseStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }
}
