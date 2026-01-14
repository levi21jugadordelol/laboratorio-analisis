package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.auditlog;

import com.laboratorio.analisis_clinico.auditlog.application.port.out.IAuditLogRepo;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.auditlog.domain.enume.ActionAuditlog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditLogJpaAdapter implements IAuditLogRepo {

    private final AuditLogJpaRepository jpaRepository;

    public AuditLogJpaAdapter(AuditLogJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(AuditLog auditLog) {
        jpaRepository.save(auditLog);
    }

    @Override
    public List<AuditLog> findByEntityNameAndEntityId(String entityName, Long entityId) {
        return jpaRepository.findByEntityNameAndEntityId(
                entityName,
                entityId
        );
    }

    @Override
    public List<AuditLog> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public List<AuditLog> findByActions(List<ActionAuditlog> actions) {
        return jpaRepository.findByActionIn(actions);
    }

    @Override
    public List<AuditLog> findByPacienteId(Long pacienteId) {
        return jpaRepository.findByEntityNameAndEntityId(
                "Paciente",
                pacienteId
        );
    }
}
