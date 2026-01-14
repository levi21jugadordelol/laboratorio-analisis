package com.laboratorio.analisis_clinico.auditlog.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class ConsultarHistorialCambiosPorEntidad {

    private final IAuditLogRepo auditLogRepo;

    public ConsultarHistorialCambiosPorEntidad(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(
            String entityName,
            Long entityId
    ) {
        return auditLogRepo.findByEntityNameAndEntityId(
                entityName,
                entityId
        );
    }
}

