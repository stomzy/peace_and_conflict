package com.example.pcr.repository;

import com.example.pcr.entity.ConflictPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConflictPhaseRepository extends JpaRepository<ConflictPhase, Long> {
    List<ConflictPhase> findByConflictCaseId(Long caseId);
}
