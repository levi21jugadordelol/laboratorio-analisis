package com.laboratorio.analisis_clinico.resultado.application.usecase.commands;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerHistorialVersionesResultado {

    private final IAuditLogRepo auditLogRepo;

    public VerHistorialVersionesResultado(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long resultadoId) {

        return auditLogRepo.findByEntityNameAndEntityId(
                "Resultado",
                resultadoId
        );
    }
}

