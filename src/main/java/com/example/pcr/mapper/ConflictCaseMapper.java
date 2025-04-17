package com.example.pcr.mapper;

import com.example.pcr.dto.reponse.CaseNoteResponseDTO;
import com.example.pcr.dto.reponse.ConflictCaseResponseDTO;
import com.example.pcr.dto.reponse.ConflictPhaseResponseDTO;
import com.example.pcr.dto.request.ConflictCaseRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pcr.mapper.UserMapper.toUserResponseDTO;

public class ConflictCaseMapper {
    public static ConflictCaseResponseDTO toConflictCaseResponseDTO(ConflictCase entity) {
        if (entity == null) return null;

        ConflictCaseResponseDTO dto = new ConflictCaseResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());
        dto.setStatus(entity.getStatus());
        dto.setDateReported(entity.getDateReported());

        dto.setReporter(toUserResponseDTO(entity.getReporter()));
        dto.setAssignedMediator(toUserResponseDTO(entity.getAssignedMediator()));

        if (entity.getNotes() != null) {
            List<CaseNoteResponseDTO> notes = entity.getNotes().stream()
                    .map(CaseNoteMapper::toCaseNoteResponseDTO)
                    .collect(Collectors.toList());
            dto.setNotes(notes);
        }

        if (entity.getPhases() != null) {
            List<ConflictPhaseResponseDTO> phases = entity.getPhases().stream()
                    .map(ConflictPhaseMapper::toConflictPhaseResponseDTO)
                    .collect(Collectors.toList());
            dto.setPhases(phases);
        }

        return dto;
    }

    public static ConflictCase toConflictCaseEntity(ConflictCaseRequestDTO dto, User reporter, User mediator) {
        ConflictCase entity = new ConflictCase();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setStatus(ConflictCase.CaseStatus.OPEN);
        entity.setDateReported(LocalDate.now());
        entity.setReporter(reporter);
        entity.setAssignedMediator(mediator);
        return entity;
    }

}
