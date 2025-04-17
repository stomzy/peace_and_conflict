package com.example.pcr.repository;

import com.example.pcr.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByConflictCaseId(Long caseId);
}
