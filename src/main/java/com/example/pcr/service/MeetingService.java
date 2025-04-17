package com.example.pcr.service;

import com.example.pcr.dto.reponse.MeetingResponseDTO;
import com.example.pcr.dto.request.MeetingRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.Meeting;
import com.example.pcr.entity.User;
import com.example.pcr.mapper.MeetingMapper;
import com.example.pcr.repository.CaseNoteRepository;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.MeetingRepository;
import com.example.pcr.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private ConflictCaseRepository conflictCaseRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public MeetingResponseDTO scheduleMeeting(Long caseId, MeetingRequestDTO request) {
        ConflictCase conflictCase = conflictCaseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Conflict case not found"));

        List<User> participants = userRepository.findAllById(request.getParticipantIds());

        Meeting meeting = MeetingMapper.toMeetingEntity(request, conflictCase, participants);
        Meeting saved = meetingRepository.save(meeting);

        return MeetingMapper.toMeetingResponseDTO(saved);
    }

    public List<MeetingResponseDTO> getMeetings(Long caseId) {
        return meetingRepository.findByConflictCaseId(caseId)
                .stream()
                .map(MeetingMapper::toMeetingResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }

}
