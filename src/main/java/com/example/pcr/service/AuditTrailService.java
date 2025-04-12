package com.example.pcr.service;

import com.example.pcr.dto.AuditTrailDTO;
import com.example.pcr.entity.AuditTrail;
import com.example.pcr.mapper.AuditTrailMapper;
import com.example.pcr.repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditTrailService {
    @Autowired
    private AuditTrailRepository auditTrailRepository;

    public void logAction(String username, String action, String entityName, String entityId, String details) {
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.setUsername(username);
        auditTrail.setAction(action);
        auditTrail.setEntityName(entityName);
        auditTrail.setEntityId(entityId);
        auditTrail.setDetails(details);
        auditTrail.setTimestamp(LocalDateTime.now());
        auditTrailRepository.save(auditTrail);
    }

    public List<AuditTrailDTO> getAllAuditLogs(Pageable pageable) {
        List<AuditTrail> auditTrails = auditTrailRepository.findAllByOrderByTimestampDesc(pageable);
        return AuditTrailMapper.toDTOList(auditTrails);
    }
}
