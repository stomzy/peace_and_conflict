package com.example.pcr.repository;

import com.example.pcr.entity.CaseNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseNoteRepository extends JpaRepository<CaseNote, Long> {
    List<CaseNote> findByConflictCaseId(Long caseId);
}
