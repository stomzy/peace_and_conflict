package com.example.pcr.controller;

import com.example.pcr.dto.reponse.ConflictPhaseResponseDTO;
import com.example.pcr.dto.request.ConflictPhaseRequestDTO;
import com.example.pcr.repository.ConflictPhaseRepository;
import com.example.pcr.service.ConflictPhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConflictPhaseController {
    @Autowired
    private ConflictPhaseService conflictPhaseService;

    @PostMapping("/{caseId}/phases")
    public ResponseEntity<ConflictPhaseResponseDTO> addPhase(
            @PathVariable Long caseId,
            @RequestBody ConflictPhaseRequestDTO dto
    ) {
        return ResponseEntity.ok(conflictPhaseService.addPhase(caseId, dto));
    }

    @GetMapping("/{caseId}/phases")
    public ResponseEntity<List<ConflictPhaseResponseDTO>> getPhases(@PathVariable Long caseId) {
        return ResponseEntity.ok(conflictPhaseService.getPhases(caseId));
    }

    @PutMapping("/phases/{phaseId}")
    public ResponseEntity<ConflictPhaseResponseDTO> updatePhase(
            @PathVariable Long phaseId,
            @RequestBody ConflictPhaseRequestDTO dto
    ) {
        return ResponseEntity.ok(conflictPhaseService.updatePhase(phaseId, dto));
    }

    @DeleteMapping("/phases/{phaseId}")
    public ResponseEntity<Void> deletePhase(@PathVariable Long phaseId) {
        conflictPhaseService.deletePhase(phaseId);
        return ResponseEntity.noContent().build();
    }
}
