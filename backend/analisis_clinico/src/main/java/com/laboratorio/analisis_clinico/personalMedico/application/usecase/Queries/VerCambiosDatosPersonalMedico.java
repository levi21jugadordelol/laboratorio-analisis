package com.laboratorio.analisis_clinico.personalMedico.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerCambiosDatosPersonalMedico {

    private final IAuditLogRepo auditLogRepo;

    public VerCambiosDatosPersonalMedico(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long personalMedicoId) {
        return auditLogRepo.findByEntityNameAndEntityId(
                "PersonalMedico",
                personalMedicoId
        );
    }
}

