package com.example.pcr.mapper;

import com.example.pcr.dto.AuditTrailDTO;
import com.example.pcr.entity.AuditTrail;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AuditTrailMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static AuditTrailDTO toDTO(AuditTrail auditTrail) {
        return new AuditTrailDTO(
                auditTrail.getUsername(),
                auditTrail.getAction(),
                auditTrail.getEntityName(),
                auditTrail.getEntityId(),
                auditTrail.getDetails(),
                auditTrail.getTimestamp().format(FORMATTER)
        );
    }

    public static List<AuditTrailDTO> toDTOList(List<AuditTrail> auditTrails) {
        return auditTrails.stream()
                .map(AuditTrailMapper::toDTO)
                .toList();
    }
}
