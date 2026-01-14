package com.laboratorio.analisis_clinico.paciente.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerHistorialCambiosPaciente {

    private final IAuditLogRepo auditLogRepo;

    public VerHistorialCambiosPaciente(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long pacienteId) {

        return auditLogRepo.findByEntityNameAndEntityId(
                "Paciente",
                pacienteId
        );
    }
}

