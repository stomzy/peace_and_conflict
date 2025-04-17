package com.example.pcr.controller;

import com.example.pcr.dto.reponse.ConflictCaseResponseDTO;
import com.example.pcr.dto.request.ConflictCaseRequestDTO;
import com.example.pcr.service.ConflictCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConflictCaseController {
    @Autowired
    private ConflictCaseService conflictCaseService;

    @GetMapping("/conflict-cases")
    public ResponseEntity<List<ConflictCaseResponseDTO>> getAllCases() {
        return ResponseEntity.ok(conflictCaseService.getAllConflictCases());
    }

    @GetMapping("/conflict-cases/{id}")
    public ResponseEntity<ConflictCaseResponseDTO> getCaseById(@PathVariable Long id) {
        return ResponseEntity.ok(conflictCaseService.getConflictCaseById(id));
    }

    @PostMapping("/conflict-cases")
    public ResponseEntity<ConflictCaseResponseDTO> createCase(@RequestBody ConflictCaseRequestDTO dto) {
        return ResponseEntity.ok(conflictCaseService.createConflictCase(dto));
    }

    @PutMapping("/conflict-cases/{id}")
    public ResponseEntity<ConflictCaseResponseDTO> updateCase(
            @PathVariable Long id,
            @RequestBody ConflictCaseRequestDTO dto
    ) {
        return ResponseEntity.ok(conflictCaseService.updateCase(id, dto));
    }

    @DeleteMapping("/conflict-cases/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable Long id) {
        conflictCaseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }
}
