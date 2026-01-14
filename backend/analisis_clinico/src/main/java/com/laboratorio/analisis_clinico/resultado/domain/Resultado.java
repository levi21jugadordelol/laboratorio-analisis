package com.laboratorio.analisis_clinico.resultado.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "resultado")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultado;

    /**
     * Resultado siempre pertenece a un análisis ejecutado.
     */
    @Column(nullable = false)
    private Long ordenAnalisisId;

    /**
     * Contenido clínico del resultado.
     */
    @Column(nullable = false, columnDefinition = "json")
    private Map<String, Object> resultadoJson;

    /**
     * Fecha en que se registró el resultado.
     * Inmutable.
     */
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Observaciones clínicas adicionales.
     */
    @Column(length = 255)
    private String observacion;

    /**
     * Usuario responsable de la creación/modificación.
     */
    @Column(nullable = false)
    private Long createdByUsuario;

    /**
     * Versionado clínico del resultado.
     */
    @Column(nullable = false)
    private double version;

    /**
     * Indica si el resultado fue validado clínicamente.
     */
    @Column(nullable = false)
    private boolean validado;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Resultado(
            Long ordenAnalisisId,
            Map<String, Object> resultadoJson,
            String observacion,
            Long createdByUsuario
    ) {
        validarOrdenAnalisis(ordenAnalisisId);
        validarResultado(resultadoJson);
        validarUsuario(createdByUsuario);

        this.ordenAnalisisId = ordenAnalisisId;
        this.resultadoJson = resultadoJson;
        this.observacion = normalizar(observacion);
        this.createdByUsuario = createdByUsuario;
        this.fechaRegistro = LocalDateTime.now(); // regla de creación
        this.version = 1.0;
        this.validado = false; // regla de creación
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    /**
     * Valida clínicamente el resultado.
     * Acción irreversible.
     */
    public void validar() {
        if (this.validado) {
            throw new IllegalStateException(
                    "El resultado ya fue validado."
            );
        }
        this.validado = true;
    }

    /**
     * Crea una nueva versión del resultado.
     * El resultado actual debe NO estar validado.
     */
    public Resultado crearNuevaVersion(
            Map<String, Object> nuevoResultado,
            String motivo,
            Long usuarioId
    ) {
        if (this.validado) {
            throw new IllegalStateException(
                    "No se puede modificar un resultado validado."
            );
        }

        validarResultado(nuevoResultado);
        validarMotivo(motivo);
        validarUsuario(usuarioId);

        return new Resultado(
                this.ordenAnalisisId,
                nuevoResultado,
                motivo,
                usuarioId
        ).conVersion(this.version + 1);
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaValidado() {
        return this.validado;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarOrdenAnalisis(Long ordenAnalisisId) {
        if (ordenAnalisisId == null) {
            throw new IllegalArgumentException(
                    "El resultado debe estar asociado a un análisis."
            );
        }
    }

    private void validarResultado(Map<String, Object> resultado) {
        if (resultado == null || resultado.isEmpty()) {
            throw new IllegalArgumentException(
                    "El resultado clínico no puede estar vacío."
            );
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "El usuario responsable del resultado es obligatorio."
            );
        }
    }

    private void validarMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La modificación del resultado requiere un motivo."
            );
        }
    }

    private Resultado conVersion(double nuevaVersion) {
        this.version = nuevaVersion;
        return this;
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

