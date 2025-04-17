package com.example.pcr;

import com.example.pcr.controller.MeetingController;
import com.example.pcr.dto.reponse.MeetingResponseDTO;
import com.example.pcr.dto.request.MeetingRequestDTO;
import com.example.pcr.service.MeetingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
class MeetingControllerTest {

    private MeetingController meetingController;

    @Mock
    private MeetingService meetingService;

    @BeforeEach
    void setUp() {
        meetingController = new MeetingController();
        ReflectionTestUtils.setField(meetingController, "meetingService", meetingService);
    }

    // Test for POST /{caseId}/meetings
    @Test
    void shouldScheduleMeetingSuccessfully() {
        // Arrange
        Long caseId = 1L;
        MeetingRequestDTO requestDTO = createMeetingRequestDTO();
        MeetingResponseDTO responseDTO = createMeetingResponseDTO();

        Mockito.when(meetingService.scheduleMeeting(eq(caseId), eq(requestDTO)))
                .thenReturn(responseDTO);

        // Act
        ResponseEntity<MeetingResponseDTO> response = meetingController.scheduleMeeting(caseId, requestDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getTopic()).isEqualTo("Zoom");
        assertThat(response.getBody().getLocation()).isEqualTo("Abuja");

        Mockito.verify(meetingService).scheduleMeeting(eq(caseId), eq(requestDTO));
    }

    // Test for GET /{caseId}/meetings
    @Test
    void shouldGetMeetingsSuccessfully() {
        // Arrange
        Long caseId = 1L;
        List<MeetingResponseDTO> meetings = List.of(createMeetingResponseDTO());

        Mockito.when(meetingService.getMeetings(eq(caseId)))
                .thenReturn(meetings);

        // Act
        ResponseEntity<List<MeetingResponseDTO>> response = meetingController.getMeetings(caseId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().getFirst().getId()).isEqualTo(1L);

        Mockito.verify(meetingService).getMeetings(eq(caseId));
    }

    // Test for DELETE /meetings/{meetingId}
    @Test
    void shouldDeleteMeetingSuccessfully() {
        // Arrange
        Long meetingId = 1L;

        Mockito.doNothing().when(meetingService).deleteMeeting(eq(meetingId));

        // Act
        ResponseEntity<Void> response = meetingController.deleteMeeting(meetingId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();

        Mockito.verify(meetingService).deleteMeeting(eq(meetingId));
    }

    // Helper methods to create DTOs
    private MeetingRequestDTO createMeetingRequestDTO() {
        MeetingRequestDTO dto = new MeetingRequestDTO();
        dto.setScheduledAt(LocalDateTime.now());
        dto.setNotes("Resolution");
        dto.setTopic("Zoom");
        dto.setLocation("Abuja");
        return dto;
    }

    private MeetingResponseDTO createMeetingResponseDTO() {
        MeetingResponseDTO dto = new MeetingResponseDTO();
        dto.setId(1L);
        dto.setScheduledAt(LocalDateTime.now());
        dto.setNotes("Resolution");
        dto.setTopic("Zoom");
        dto.setLocation("Abuja");
        return dto;
    }
}
