package com.laboratorio.analisis_clinico.auditlog.adapter.in.web.dto;

import com.laboratorio.analisis_clinico.auditlog.domain.enume.ActionAuditlog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class AuditLogDtoResponse {

    private Long idAudit;
    private String entityName;
    private Long entityId;
    private ActionAuditlog action;
    private Long userId;
    private LocalDateTime fecha;
    private Map<String, Object> diffJson;
    private String reason;
}

