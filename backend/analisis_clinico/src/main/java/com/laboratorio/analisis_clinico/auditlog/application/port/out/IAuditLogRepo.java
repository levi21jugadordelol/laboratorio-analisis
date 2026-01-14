package com.laboratorio.analisis_clinico.auditlog.application.port.out;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.auditlog.domain.enume.ActionAuditlog;

import java.util.List;

public interface IAuditLogRepo {

    void save(AuditLog auditLog);

    List<AuditLog> findByEntityNameAndEntityId(
            String entityName,
            Long entityId
    );

    List<AuditLog> findByUserId(Long userId);

    List<AuditLog> findByActions(List<ActionAuditlog> actions);

    List<AuditLog> findByPacienteId(Long pacienteId);
}

