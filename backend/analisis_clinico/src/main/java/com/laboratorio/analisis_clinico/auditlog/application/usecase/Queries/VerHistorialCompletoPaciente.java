package com.laboratorio.analisis_clinico.auditlog.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerHistorialCompletoPaciente {

    private final IAuditLogRepo auditLogRepo;

    public VerHistorialCompletoPaciente(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long pacienteId) {
        return auditLogRepo.findByPacienteId(pacienteId);
    }
}

