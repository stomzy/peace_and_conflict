package com.example.pcr.mapper;

import com.example.pcr.dto.reponse.CaseNoteResponseDTO;
import com.example.pcr.dto.request.CaseNoteRequestDTO;
import com.example.pcr.entity.CaseNote;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.User;

import java.time.LocalDateTime;

import static com.example.pcr.mapper.UserMapper.toUserResponseDTO;

public class CaseNoteMapper {
    public static CaseNoteResponseDTO toCaseNoteResponseDTO(CaseNote note) {
        if (note == null) return null;
        CaseNoteResponseDTO dto = new CaseNoteResponseDTO();
        dto.setId(note.getId());
        dto.setNote(note.getNote());
        dto.setCreatedAt(note.getCreatedAt());
        dto.setAuthor(toUserResponseDTO(note.getAuthor()));
        return dto;
    }

    public static CaseNote toCaseNoteEntity(CaseNoteRequestDTO dto, ConflictCase conflictCase, User author) {
        CaseNote note = new CaseNote();
        note.setNote(dto.getNote());
        note.setConflictCase(conflictCase);
        note.setAuthor(author);
        note.setCreatedAt(LocalDateTime.now());
        return note;
    }
}
