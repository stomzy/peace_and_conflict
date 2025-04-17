package com.example.pcr;

import com.example.pcr.dto.reponse.DocumentResponseDTO;
import com.example.pcr.dto.request.DocumentUploadRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.Document;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.DocumentRepository;
import com.example.pcr.service.DocumentService;
import com.example.pcr.service.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DocumentServiceTests {
    @InjectMocks
    private DocumentService documentService;

    @Mock
    private ConflictCaseRepository conflictCaseRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private FileStorageService fileStorageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadDocument() {
        Long caseId = 1L;
        String fileUrl = "/uploads/doc.pdf";
        MultipartFile file = mock(MultipartFile.class);
        DocumentUploadRequestDTO dto = new DocumentUploadRequestDTO("doc.pdf", "description");

        ConflictCase conflictCase = new ConflictCase();
        conflictCase.setId(caseId);

        Document document = new Document();
        document.setId(1L);
        document.setFileName("doc.pdf");
        document.setUrl(fileUrl);
        document.setConflictCase(conflictCase);

        when(conflictCaseRepository.findById(caseId)).thenReturn(Optional.of(conflictCase));
        when(fileStorageService.storeFile(file)).thenReturn(fileUrl);
        when(documentRepository.save(any())).thenReturn(document);

        DocumentResponseDTO response = documentService.uploadDocument(caseId, dto, file);

        assertThat(response).isNotNull();
        assertThat(response.getFileName()).isEqualTo("doc.pdf");
        assertThat(response.getUrl()).isEqualTo(fileUrl);
    }

    @Test
    void testGetDocuments() {
        Long caseId = 1L;
        Document doc = new Document();
        doc.setId(1L);
        doc.setFileName("file1.pdf");
        doc.setUrl("/path/file1.pdf");

        when(documentRepository.findByConflictCaseId(caseId)).thenReturn(List.of(doc));

        List<DocumentResponseDTO> docs = documentService.getDocuments(caseId);

        assertThat(docs).hasSize(1);
        assertThat(docs.getFirst().getFileName()).isEqualTo("file1.pdf");
    }


    @Test
    void testDeleteDocument() {
        Long docId = 10L;

        documentService.deleteDocument(docId);

        verify(documentRepository).deleteById(docId);
    }

}
