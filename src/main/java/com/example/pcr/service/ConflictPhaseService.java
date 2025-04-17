package com.example.pcr.service;

import com.example.pcr.dto.reponse.ConflictPhaseResponseDTO;
import com.example.pcr.dto.request.ConflictPhaseRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.ConflictPhase;
import com.example.pcr.mapper.ConflictPhaseMapper;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.ConflictPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConflictPhaseService {
    @Autowired
    private ConflictCaseRepository conflictCaseRepository;
    @Autowired
    private ConflictPhaseRepository conflictPhaseRepository;

    public ConflictPhaseResponseDTO addPhase(Long caseId, ConflictPhaseRequestDTO dto) {
        ConflictCase conflictCase = conflictCaseRepository.findById(caseId).orElseThrow();
        ConflictPhase phase = ConflictPhaseMapper.toConflictPhaseEntity(dto, conflictCase);
        return ConflictPhaseMapper.toConflictPhaseResponseDTO(conflictPhaseRepository.save(phase));
    }

    public List<ConflictPhaseResponseDTO> getPhases(Long caseId) {
        return conflictPhaseRepository.findByConflictCaseId(caseId)
                .stream()
                .map(ConflictPhaseMapper::toConflictPhaseResponseDTO)
                .collect(Collectors.toList());
    }
    public ConflictPhaseResponseDTO updatePhase(Long phaseId, ConflictPhaseRequestDTO dto) {
        ConflictPhase existing = conflictPhaseRepository.findById(phaseId).orElseThrow();
        existing.setPhaseName(dto.getPhaseName());
        existing.setDetails(dto.getDetails());
        existing.setStatus(dto.getStatus());
        return ConflictPhaseMapper.toConflictPhaseResponseDTO(conflictPhaseRepository.save(existing));
    }

    public void deletePhase(Long id) {
        conflictPhaseRepository.deleteById(id);
    }

}
