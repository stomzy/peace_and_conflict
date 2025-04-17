package com.example.pcr.dto.reponse;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingResponseDTO {
    private Long id;
    private String topic;
    private LocalDateTime scheduledAt;
    private String location;
    private String notes;

    private List<UserResponseDTO> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<UserResponseDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserResponseDTO> participants) {
        this.participants = participants;
    }
}
