package com.laboratorio.analisis_clinico.orden.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerHistorialCambiosOrden {

    private final IAuditLogRepo auditLogRepo;

    public VerHistorialCambiosOrden(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long ordenId) {

        return auditLogRepo.findByEntityNameAndEntityId(
                "Orden",
                ordenId
        );
    }
}


