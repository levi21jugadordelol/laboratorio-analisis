package com.laboratorio.analisis_clinico.usuario.application.usecase.Queries;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;

import java.util.List;

public class VerAccionesUsuario {

    private final IAuditLogRepo auditLogRepo;

    public VerAccionesUsuario(IAuditLogRepo auditLogRepo) {
        this.auditLogRepo = auditLogRepo;
    }

    public List<AuditLog> ejecutar(Long userId) {
        return auditLogRepo.findByUserId(userId);
    }
}

