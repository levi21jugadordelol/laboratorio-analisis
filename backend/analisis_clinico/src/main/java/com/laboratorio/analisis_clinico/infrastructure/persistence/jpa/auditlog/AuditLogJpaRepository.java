package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.auditlog;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.auditlog.domain.enume.ActionAuditlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogJpaRepository
        extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByEntityNameAndEntityId(
            String entityName,
            Long entityId
    );

    List<AuditLog> findByUserId(Long userId);

    List<AuditLog> findByActionIn(List<ActionAuditlog> actions);

    // ⚠️ Nota importante abajo
    List<AuditLog> findByEntityNameAndEntityIdIn(
            String entityName,
            List<Long> entityIds
    );
}

