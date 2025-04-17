package com.example.pcr;

import com.example.pcr.dto.reponse.ConflictCaseResponseDTO;
import com.example.pcr.dto.reponse.UserResponseDTO;
import com.example.pcr.dto.request.ConflictCaseRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.User;
import com.example.pcr.mapper.ConflictCaseMapper;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.UserRepository;
import com.example.pcr.service.ConflictCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConflictCaseServiceTests {
    @Mock private ConflictCaseRepository conflictCaseRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private ConflictCaseService conflictCaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConflictCase_success() {
        ConflictCaseRequestDTO requestDTO = new ConflictCaseRequestDTO();
        requestDTO.setTitle("Test Case");
        requestDTO.setDescription("Test Desc");
        requestDTO.setLocation("City A");
        requestDTO.setReporterId(1L);
        requestDTO.setAssignedMediatorId(2L);

        User reporter = new User(); reporter.setId(1L); reporter.setFullName("Reporter");
        User mediator = new User(); mediator.setId(2L); mediator.setFullName("Mediator");

        ConflictCase entity = new ConflictCase();
        entity.setId(100L);
        entity.setTitle("Test Case");
        entity.setReporter(reporter);
        entity.setAssignedMediator(mediator);

        when(userRepository.findById(1L)).thenReturn(Optional.of(reporter));
        when(userRepository.findById(2L)).thenReturn(Optional.of(mediator));
        when(conflictCaseRepository.save(any())).thenReturn(entity);

        ConflictCaseResponseDTO result = conflictCaseService.createConflictCase(requestDTO);

        assertThat(result.getTitle()).isEqualTo("Test Case");
        assertThat(result.getReporter().getFullName()).isEqualTo(reporter.getFullName());

    }

    @Test
    void testCreateConflictCase_reporterNotFound() {
        ConflictCaseRequestDTO request = new ConflictCaseRequestDTO();
        request.setReporterId(1L);
        request.setAssignedMediatorId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                conflictCaseService.createConflictCase(request));
    }

    @Test
    void testGetConflictCaseById_found() {
        ConflictCase entity = new ConflictCase();
        entity.setId(10L);
        entity.setTitle("Conflict");

        when(conflictCaseRepository.findById(10L)).thenReturn(Optional.of(entity));

        ConflictCaseResponseDTO result = conflictCaseService.getConflictCaseById(10L);
        assertThat(result.getTitle()).isEqualTo("Conflict");
    }

    @Test
    void testGetConflictCaseById_notFound() {
        when(conflictCaseRepository.findById(10L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () ->
                conflictCaseService.getConflictCaseById(10L));
    }

    @Test
    void testGetAllConflictCases() {
        ConflictCase c1 = new ConflictCase(); c1.setId(1L); c1.setTitle("C1");
        ConflictCase c2 = new ConflictCase(); c2.setId(2L); c2.setTitle("C2");

        when(conflictCaseRepository.findAll()).thenReturn(List.of(c1, c2));

        List<ConflictCaseResponseDTO> result = conflictCaseService.getAllConflictCases();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void testUpdateCase_success() {
        ConflictCaseRequestDTO dto = new ConflictCaseRequestDTO();
        dto.setTitle("Updated Title");
        dto.setDescription("Updated Description");
        dto.setLocation("Updated Location");

        ConflictCase existing = new ConflictCase();
        existing.setId(1L);
        existing.setTitle("Old Title");

        when(conflictCaseRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(conflictCaseRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ConflictCaseResponseDTO result = conflictCaseService.updateCase(1L, dto);
        assertThat(result.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void testDeleteCase_success() {
        doNothing().when(conflictCaseRepository).deleteById(5L);
        conflictCaseService.deleteCase(5L);
        verify(conflictCaseRepository, times(1)).deleteById(5L);
    }


}
