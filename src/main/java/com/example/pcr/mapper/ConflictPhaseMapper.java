package com.example.pcr.mapper;

import com.example.pcr.dto.reponse.ConflictPhaseResponseDTO;
import com.example.pcr.dto.request.ConflictPhaseRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.ConflictPhase;

import java.time.LocalDateTime;

public class ConflictPhaseMapper {
    public static ConflictPhaseResponseDTO toConflictPhaseResponseDTO(ConflictPhase phase) {
        if (phase == null) return null;
        ConflictPhaseResponseDTO dto = new ConflictPhaseResponseDTO();
        dto.setId(phase.getId());
        dto.setPhaseName(phase.getPhaseName());
        dto.setDetails(phase.getDetails());
        dto.setStatus(phase.getStatus());
        dto.setStartedAt(phase.getStartedAt());
        dto.setEndedAt(phase.getEndedAt());
        return dto;
    }

    public static ConflictPhase toConflictPhaseEntity(ConflictPhaseRequestDTO dto, ConflictCase conflictCase) {
        ConflictPhase phase = new ConflictPhase();
        phase.setPhaseName(dto.getPhaseName());
        phase.setDetails(dto.getDetails());
        phase.setStatus(dto.getStatus());
        phase.setConflictCase(conflictCase);
        phase.setStartedAt(LocalDateTime.now());
        return phase;
    }

}
