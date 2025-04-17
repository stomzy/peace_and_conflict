package com.example.pcr.controller;

import com.example.pcr.dto.reponse.CaseNoteResponseDTO;
import com.example.pcr.dto.request.CaseNoteRequestDTO;
import com.example.pcr.service.CaseNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CaseNoteController {
    @Autowired
    private CaseNoteService caseNoteService;

    @PostMapping("/{caseId}/notes")
    public ResponseEntity<CaseNoteResponseDTO> addNote(
            @PathVariable Long caseId,
            @RequestBody CaseNoteRequestDTO dto
    ) {
        return ResponseEntity.ok(caseNoteService.addCaseNote(caseId, dto));
    }

    @GetMapping("/{caseId}/notes")
    public ResponseEntity<List<CaseNoteResponseDTO>> getNotes(@PathVariable Long caseId) {
        return ResponseEntity.ok(caseNoteService.getNotes(caseId));
    }

    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId) {
        caseNoteService.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }

}
