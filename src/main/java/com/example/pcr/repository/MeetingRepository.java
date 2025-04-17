package com.example.pcr.repository;

import com.example.pcr.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByConflictCaseId(Long caseId);
}
