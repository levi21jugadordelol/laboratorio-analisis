package com.laboratorio.analisis_clinico.auditlog.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.auditlog.domain.enume.ActionAuditlog;

import java.util.List;

public class ConsultarAccionesCriticas {

    private final IAuditLogRepo auditLogRepo;

    public ConsultarAccionesCriticas(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar() {
        return auditLogRepo.findByActions(
                List.of(
                        ActionAuditlog.DELETE,
                        ActionAuditlog.VALIDATE
                )
        );
    }
}

