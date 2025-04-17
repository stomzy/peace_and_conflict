package com.example.pcr.mapper;

import com.example.pcr.dto.reponse.MeetingResponseDTO;
import com.example.pcr.dto.reponse.UserResponseDTO;
import com.example.pcr.dto.request.MeetingRequestDTO;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.Meeting;
import com.example.pcr.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class MeetingMapper {
    public static MeetingResponseDTO toMeetingResponseDTO(Meeting meeting) {
        MeetingResponseDTO dto = new MeetingResponseDTO();
        dto.setId(meeting.getId());
        dto.setTopic(meeting.getTopic());
        dto.setScheduledAt(meeting.getScheduledAt());
        dto.setLocation(meeting.getLocation());
        dto.setNotes(meeting.getNotes());

        List<UserResponseDTO> participants = meeting.getParticipants().stream()
                .map(UserMapper::toUserResponseDTO)
                .collect(Collectors.toList());
        dto.setParticipants(participants);

        return dto;
    }

    public static Meeting toMeetingEntity(MeetingRequestDTO dto, ConflictCase conflictCase, List<User> participants) {
        Meeting meeting = new Meeting();
        meeting.setTopic(dto.getTopic());
        meeting.setScheduledAt(dto.getScheduledAt());
        meeting.setLocation(dto.getLocation());
        meeting.setNotes(dto.getNotes());
        meeting.setConflictCase(conflictCase);
        meeting.setParticipants(participants);
        return meeting;
    }

}
