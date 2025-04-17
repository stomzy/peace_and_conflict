package com.example.pcr.controller;

import com.example.pcr.dto.reponse.MeetingResponseDTO;
import com.example.pcr.dto.request.MeetingRequestDTO;
import com.example.pcr.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping("/{caseId}/meetings")
    public ResponseEntity<MeetingResponseDTO> scheduleMeeting(
            @PathVariable Long caseId,
            @RequestBody MeetingRequestDTO dto
    ) {
        return ResponseEntity.ok(meetingService.scheduleMeeting(caseId, dto));
    }

    @GetMapping("/{caseId}/meetings")
    public ResponseEntity<List<MeetingResponseDTO>> getMeetings(@PathVariable Long caseId) {
        return ResponseEntity.ok(meetingService.getMeetings(caseId));
    }

    @DeleteMapping("/meetings/{meetingId}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long meetingId) {
        meetingService.deleteMeeting(meetingId);
        return ResponseEntity.noContent().build();
    }
}
