package com.example.pcr.service;

import com.example.pcr.dto.reponse.CaseNoteResponseDTO;
import com.example.pcr.dto.request.CaseNoteRequestDTO;
import com.example.pcr.entity.CaseNote;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.User;
import com.example.pcr.mapper.CaseNoteMapper;
import com.example.pcr.repository.CaseNoteRepository;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseNoteService {
    @Autowired
    private CaseNoteRepository caseNoteRepository;
    @Autowired
    private ConflictCaseRepository conflictCaseRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CaseNoteResponseDTO addCaseNote(Long caseId, CaseNoteRequestDTO request) {
        ConflictCase conflictCase = conflictCaseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Conflict case not found"));

        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        CaseNote note = CaseNoteMapper.toCaseNoteEntity(request, conflictCase, author);
        CaseNote saved = caseNoteRepository.save(note);

        return CaseNoteMapper.toCaseNoteResponseDTO(saved);
    }

    public List<CaseNoteResponseDTO> getNotes(Long caseId) {
        return caseNoteRepository.findByConflictCaseId(caseId)
                .stream()
                .map(CaseNoteMapper::toCaseNoteResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteNote(Long noteId) {
        caseNoteRepository.deleteById(noteId);
    }
}
