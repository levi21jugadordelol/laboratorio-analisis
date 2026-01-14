package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerCambiosEstadoAnalisis {

    private final IAuditLogRepo auditLogRepo;

    public VerCambiosEstadoAnalisis(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long ordenAnalisisId) {

        return auditLogRepo.findByEntityNameAndEntityId(
                "OrdenAnalisis",
                ordenAnalisisId
        );
    }
}

