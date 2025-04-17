package com.example.pcr.mapper;

import com.example.pcr.dto.reponse.DocumentResponseDTO;
import com.example.pcr.dto.request.DocumentUploadRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.Document;

import java.time.LocalDateTime;

public class DocumentMapper {
    public static DocumentResponseDTO toDocumentResponseDTO(Document doc) {
        DocumentResponseDTO dto = new DocumentResponseDTO();
        dto.setId(doc.getId());
        dto.setFileName(doc.getFileName());
        dto.setFileType(doc.getFileType());
        dto.setUrl(doc.getUrl());
        dto.setUploadedAt(doc.getUploadedAt());
        return dto;
    }

    public static Document toDocumentEntity(DocumentUploadRequestDTO dto, ConflictCase conflictCase, String url) {
        Document doc = new Document();
        doc.setFileName(dto.getFileName());
        doc.setFileType(dto.getFileType());
        doc.setUrl(url);
        doc.setConflictCase(conflictCase);
        doc.setUploadedAt(LocalDateTime.now());
        return doc;
    }
}
