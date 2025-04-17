package com.example.pcr.service;

import com.example.pcr.dto.reponse.ConflictCaseResponseDTO;
import com.example.pcr.dto.request.ConflictCaseRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.User;
import com.example.pcr.mapper.ConflictCaseMapper;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConflictCaseService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConflictCaseRepository conflictCaseRepository;

    @Transactional
    public ConflictCaseResponseDTO createConflictCase(ConflictCaseRequestDTO request) {
        User reporter = userRepository.findById(request.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));

        User mediator = userRepository.findById(request.getAssignedMediatorId())
                .orElseThrow(() -> new RuntimeException("Mediator not found"));

        ConflictCase entity = ConflictCaseMapper.toConflictCaseEntity(request, reporter, mediator);
        ConflictCase saved = conflictCaseRepository.save(entity);

        return ConflictCaseMapper.toConflictCaseResponseDTO(saved);
    }

    public ConflictCaseResponseDTO getConflictCaseById(Long caseId) {
        ConflictCase entity = conflictCaseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Conflict case not found"));

        return ConflictCaseMapper.toConflictCaseResponseDTO(entity);
    }

    public List<ConflictCaseResponseDTO> getAllConflictCases() {
        return conflictCaseRepository.findAll()
                .stream()
                .map(ConflictCaseMapper::toConflictCaseResponseDTO)
                .collect(Collectors.toList());
    }

    public ConflictCaseResponseDTO updateCase(Long id, ConflictCaseRequestDTO dto) {
        ConflictCase existing = conflictCaseRepository.findById(id).orElseThrow();
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setLocation(dto.getLocation());
        return ConflictCaseMapper.toConflictCaseResponseDTO(conflictCaseRepository.save(existing));
    }

    public void deleteCase(Long id) {
        conflictCaseRepository.deleteById(id);
    }
}
