package com.example.pcr.service;

import com.example.pcr.dto.reponse.DocumentResponseDTO;
import com.example.pcr.dto.request.DocumentUploadRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.Document;
import com.example.pcr.mapper.DocumentMapper;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private ConflictCaseRepository conflictCaseRepository;
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public DocumentResponseDTO uploadDocument(Long caseId, DocumentUploadRequestDTO dto, MultipartFile file) {
        ConflictCase conflictCase = conflictCaseRepository.findById(caseId).orElseThrow(() -> new RuntimeException("Conflict case not found"));

        String fileUrl = fileStorageService.storeFile(file);

        Document doc = DocumentMapper.toDocumentEntity(dto, conflictCase, fileUrl);
        return DocumentMapper.toDocumentResponseDTO(documentRepository.save(doc));
    }

    public List<DocumentResponseDTO> getDocuments(Long caseId) {
        return documentRepository.findByConflictCaseId(caseId)
                .stream()
                .map(DocumentMapper::toDocumentResponseDTO)
                .collect(Collectors.toList());
    }
    public void deleteDocument(Long docId) {
        documentRepository.deleteById(docId);
    }
}
