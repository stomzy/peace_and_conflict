package com.example.pcr;

import com.example.pcr.controller.ConflictPhaseController;
import com.example.pcr.dto.reponse.ConflictPhaseResponseDTO;
import com.example.pcr.dto.request.ConflictPhaseRequestDTO;
import com.example.pcr.service.ConflictPhaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class ConflictPhaseControllerTests {

    private ConflictPhaseController conflictPhaseController;

    @Mock
    private ConflictPhaseService conflictPhaseService;

    @BeforeEach
    void setUp() {
        conflictPhaseController = new ConflictPhaseController();
        ReflectionTestUtils.setField(conflictPhaseController, "conflictPhaseService", conflictPhaseService);
    }

    // Helper methods to create DTOs
    private ConflictPhaseRequestDTO createConflictPhaseRequestDTO() {
        ConflictPhaseRequestDTO dto = new ConflictPhaseRequestDTO();
        dto.setPhaseName("Initial Phase");
        dto.setDetails("This is the initial phase of the conflict.");
        return dto;
    }

    private ConflictPhaseResponseDTO createConflictPhaseResponseDTO() {
        ConflictPhaseResponseDTO dto = new ConflictPhaseResponseDTO();
        dto.setId(1L);
        dto.setPhaseName("Initial Phase");
        dto.setDetails("This is the initial phase of the conflict.");
        return dto;
    }


    // Test for POST /{caseId}/phases
    @Test
    void shouldAddPhaseSuccessfully() {
        // Arrange
        Long caseId = 1L;
        ConflictPhaseRequestDTO requestDTO = createConflictPhaseRequestDTO();
        ConflictPhaseResponseDTO responseDTO = createConflictPhaseResponseDTO();

        Mockito.when(conflictPhaseService.addPhase(eq(caseId), eq(requestDTO)))
                .thenReturn(responseDTO);

        // Act
        ResponseEntity<ConflictPhaseResponseDTO> response = conflictPhaseController.addPhase(caseId, requestDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getPhaseName()).isEqualTo("Initial Phase");

        Mockito.verify(conflictPhaseService).addPhase(eq(caseId), eq(requestDTO));
    }

    @Test
    void shouldGetPhasesSuccessfully() {
        // Arrange
        Long caseId = 1L;
        List<ConflictPhaseResponseDTO> phases = List.of(createConflictPhaseResponseDTO());

        Mockito.when(conflictPhaseService.getPhases(eq(caseId)))
                .thenReturn(phases);

        // Act
        ResponseEntity<List<ConflictPhaseResponseDTO>> response = conflictPhaseController.getPhases(caseId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getId()).isEqualTo(1L);

        Mockito.verify(conflictPhaseService).getPhases(eq(caseId));
    }

    @Test
    void shouldUpdatePhaseSuccessfully() {
        // Arrange
        Long phaseId = 1L;
        ConflictPhaseRequestDTO requestDTO = createConflictPhaseRequestDTO();
        ConflictPhaseResponseDTO responseDTO = createConflictPhaseResponseDTO();

        Mockito.when(conflictPhaseService.updatePhase(eq(phaseId), eq(requestDTO)))
                .thenReturn(responseDTO);

        // Act
        ResponseEntity<ConflictPhaseResponseDTO> response = conflictPhaseController.updatePhase(phaseId, requestDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getPhaseName()).isEqualTo("Initial Phase");

        Mockito.verify(conflictPhaseService).updatePhase(eq(phaseId), eq(requestDTO));
    }

    @Test
    void shouldDeletePhaseSuccessfully() {
        // Arrange
        Long phaseId = 1L;

        Mockito.doNothing().when(conflictPhaseService).deletePhase(eq(phaseId));

        // Act
        ResponseEntity<Void> response = conflictPhaseController.deletePhase(phaseId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();

        Mockito.verify(conflictPhaseService).deletePhase(eq(phaseId));
    }
}
