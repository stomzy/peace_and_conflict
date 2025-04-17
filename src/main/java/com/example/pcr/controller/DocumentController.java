package com.example.pcr.controller;

import com.example.pcr.dto.reponse.DocumentResponseDTO;
import com.example.pcr.dto.request.DocumentUploadRequestDTO;
import com.example.pcr.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @PostMapping("/{caseId}/documents")
    public ResponseEntity<DocumentResponseDTO> uploadDocument(
            @PathVariable Long caseId,
            @RequestPart("file") MultipartFile file,
            @RequestPart("meta") DocumentUploadRequestDTO dto
    ) {
        return ResponseEntity.ok(documentService.uploadDocument(caseId, dto, file));
    }

    @GetMapping("/{caseId}/documents")
    public ResponseEntity<List<DocumentResponseDTO>> getDocuments(@PathVariable Long caseId) {
        return ResponseEntity.ok(documentService.getDocuments(caseId));
    }

    @DeleteMapping("/documents/{docId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long docId) {
        documentService.deleteDocument(docId);
        return ResponseEntity.noContent().build();
    }
}
