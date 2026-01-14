package com.laboratorio.analisis_clinico.auditlog.domain;


import com.laboratorio.analisis_clinico.auditlog.domain.enume.ActionAuditlog;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "audit_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAudit;

    @Column(nullable = false, length = 100)
    private String entityName;

    @Column(nullable = false)
    private Long entityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ActionAuditlog action;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime fecha;

    /**
     * Representa los cambios realizados.
     * El dominio no valida la estructura, solo su coherencia de uso.
     */
    @Column(columnDefinition = "json")
    private Map<String, Object> diffJson;

    @Column(length = 255)
    private String reason;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public AuditLog(
            String entityName,
            Long entityId,
            ActionAuditlog action,
            Long userId,
            Map<String, Object> diffJson,
            String reason
    ) {
        validarEntidad(entityName, entityId);
        validarAccion(action);
        validarUsuario(userId);
        validarDiffSegunAccion(action, diffJson);
        validarMotivoSegunAccion(action, reason);

        this.entityName = entityName.trim();
        this.entityId = entityId;
        this.action = action;
        this.userId = userId;
        this.diffJson = diffJson;
        this.reason = normalizar(reason);
        this.fecha = LocalDateTime.now(); // regla de creación
    }

    // =========================
    // INVARIANTES DE DOMINIO
    // =========================

    private void validarEntidad(String entityName, Long entityId) {
        if (entityName == null || entityName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre de la entidad auditada es obligatorio."
            );
        }
        if (entityId == null) {
            throw new IllegalArgumentException(
                    "El identificador de la entidad auditada es obligatorio."
            );
        }
    }

    private void validarAccion(ActionAuditlog action) {
        if (action == null) {
            throw new IllegalArgumentException(
                    "La acción auditada es obligatoria."
            );
        }
    }

    private void validarUsuario(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException(
                    "El usuario responsable de la acción es obligatorio."
            );
        }
    }

    private void validarDiffSegunAccion(ActionAuditlog action, Map<String, Object> diffJson) {
        if (action == ActionAuditlog.UPDATE && (diffJson == null || diffJson.isEmpty())) {
            throw new IllegalArgumentException(
                    "Una acción UPDATE debe registrar los cambios realizados."
            );
        }
    }

    private void validarMotivoSegunAccion(ActionAuditlog action, String reason) {
        if ((action == ActionAuditlog.DELETE || action == ActionAuditlog.VALIDATE)
                && (reason == null || reason.trim().isEmpty())) {
            throw new IllegalArgumentException(
                    "La acción " + action + " requiere un motivo."
            );
        }
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

