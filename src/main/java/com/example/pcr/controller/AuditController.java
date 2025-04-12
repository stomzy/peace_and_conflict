package com.example.pcr.controller;

import com.example.pcr.dto.AuditTrailDTO;
import com.example.pcr.service.AuditTrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {
    @Autowired
    private AuditTrailService auditTrailService;

    @GetMapping
    public ResponseEntity<List<AuditTrailDTO>> getAuditLogs(Pageable pageable) {
        return ResponseEntity.ok(auditTrailService.getAllAuditLogs(pageable));
    }
}
