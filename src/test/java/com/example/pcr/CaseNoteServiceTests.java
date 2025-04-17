package com.example.pcr;

import com.example.pcr.dto.reponse.CaseNoteResponseDTO;
import com.example.pcr.dto.request.CaseNoteRequestDTO;
import com.example.pcr.entity.CaseNote;
import com.example.pcr.entity.ConflictCase;
import com.example.pcr.entity.User;
import com.example.pcr.repository.CaseNoteRepository;
import com.example.pcr.repository.ConflictCaseRepository;
import com.example.pcr.repository.UserRepository;
import com.example.pcr.service.CaseNoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CaseNoteServiceTests {
    @InjectMocks
    private CaseNoteService caseNoteService;

    @Mock
    private CaseNoteRepository caseNoteRepository;
    @Mock
    private ConflictCaseRepository conflictCaseRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCaseNote() {
        Long caseId = 1L;
        Long authorId = 10L;

        CaseNoteRequestDTO request = new CaseNoteRequestDTO();
        request.setNote("Note content");
        request.setAuthorId(authorId);

        ConflictCase conflictCase = new ConflictCase(); conflictCase.setId(caseId);
        User author = new User(); author.setId(authorId);

        CaseNote savedNote = new CaseNote();
        savedNote.setId(100L);
        savedNote.setNote("Note content");
        savedNote.setAuthor(author);
        savedNote.setConflictCase(conflictCase);

        when(conflictCaseRepository.findById(caseId)).thenReturn(Optional.of(conflictCase));
        when(userRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(caseNoteRepository.save(any())).thenReturn(savedNote);

        CaseNoteResponseDTO response = caseNoteService.addCaseNote(caseId, request);

        assertThat(response).isNotNull();
        assertThat(response.getNote()).isEqualTo("Note content");
        assertThat(response.getAuthor().getId()).isEqualTo(authorId);
    }

    @Test
    void testGetNotes() {
        Long caseId = 1L;
        CaseNote note = new CaseNote();
        note.setId(1L);
        note.setNote("Test note");

        when(caseNoteRepository.findByConflictCaseId(caseId)).thenReturn(List.of(note));

        List<CaseNoteResponseDTO> notes = caseNoteService.getNotes(caseId);

        assertThat(notes).hasSize(1);
        assertThat(notes.getFirst().getNote()).isEqualTo("Test note");
    }

    @Test
    void testDeleteNote() {
        Long noteId = 5L;

        caseNoteService.deleteNote(noteId);

        verify(caseNoteRepository).deleteById(noteId);
    }
}
