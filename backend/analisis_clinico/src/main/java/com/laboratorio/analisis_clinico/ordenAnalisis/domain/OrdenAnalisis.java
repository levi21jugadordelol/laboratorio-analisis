package com.laboratorio.analisis_clinico.ordenAnalisis.domain;


import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orden_analisis")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdenAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenAnalisis;

    /**
     * Orden a la que pertenece este análisis.
     */
    @Column(nullable = false)
    private Long ordenId;

    /**
     * Formato con el que se ejecuta el análisis.
     */
    @Column(nullable = false)
    private Long formatoId;

    /**
     * Fecha en que el análisis empieza a ejecutarse.
     * Se asigna al iniciar el proceso.
     */
    private LocalDateTime fechaRealizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoOrdenMedicaAnalisis estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Prioridad prioridad;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public OrdenAnalisis(
            Long ordenId,
            Long formatoId,
            Prioridad prioridad
    ) {
        validarOrden(ordenId);
        validarFormato(formatoId);
        validarPrioridad(prioridad);

        this.ordenId = ordenId;
        this.formatoId = formatoId;
        this.prioridad = prioridad;
        this.estado = EstadoOrdenMedicaAnalisis.PENDIENTE; // regla de creación
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void iniciar() {
        if (this.estado != EstadoOrdenMedicaAnalisis.PENDIENTE) {
            throw new IllegalStateException(
                    "Solo un análisis pendiente puede iniciarse."
            );
        }
        this.estado = EstadoOrdenMedicaAnalisis.EN_PROCESO;
        this.fechaRealizacion = LocalDateTime.now();
    }

    public void completar() {
        if (this.estado != EstadoOrdenMedicaAnalisis.EN_PROCESO) {
            throw new IllegalStateException(
                    "Solo un análisis en proceso puede completarse."
            );
        }
        this.estado = EstadoOrdenMedicaAnalisis.COMPLETADO;
    }

    public void cancelar() {
        if (this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO) {
            throw new IllegalStateException(
                    "Un análisis completado no puede cancelarse."
            );
        }
        if (this.estado == EstadoOrdenMedicaAnalisis.CANCELADO) {
            throw new IllegalStateException(
                    "El análisis ya se encuentra cancelado."
            );
        }
        this.estado = EstadoOrdenMedicaAnalisis.CANCELADO;
    }

    public void cambiarPrioridad(Prioridad nuevaPrioridad) {
        if (this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO) {
            throw new IllegalStateException(
                    "No se puede cambiar la prioridad de un análisis completado."
            );
        }
        validarPrioridad(nuevaPrioridad);
        this.prioridad = nuevaPrioridad;
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaPendiente() {
        return this.estado == EstadoOrdenMedicaAnalisis.PENDIENTE;
    }

    public boolean estaEnProceso() {
        return this.estado == EstadoOrdenMedicaAnalisis.EN_PROCESO;
    }

    public boolean estaCompletado() {
        return this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO;
    }

    public void reprogramar(LocalDateTime nuevaFecha) {
        if (this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO) {
            throw new IllegalStateException(
                    "No se puede reprogramar un análisis completado."
            );
        }

        if (this.estado == EstadoOrdenMedicaAnalisis.CANCELADO) {
            throw new IllegalStateException(
                    "No se puede reprogramar un análisis cancelado."
            );
        }

        if (nuevaFecha == null) {
            throw new IllegalArgumentException(
                    "La nueva fecha de realización es obligatoria."
            );
        }

        if (nuevaFecha.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "La nueva fecha de realización no puede ser pasada."
            );
        }

        this.fechaRealizacion = nuevaFecha;
    }

    public void cambiarFormato(Long nuevoFormatoId) {
        if (this.estado != EstadoOrdenMedicaAnalisis.PENDIENTE) {
            throw new IllegalStateException(
                    "Solo se puede cambiar el formato de un análisis pendiente."
            );
        }

        if (nuevoFormatoId == null) {
            throw new IllegalArgumentException(
                    "El nuevo formato es obligatorio."
            );
        }

        this.formatoId = nuevoFormatoId;
    }



    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarOrden(Long ordenId) {
        if (ordenId == null) {
            throw new IllegalArgumentException(
                    "El análisis debe estar asociado a una orden."
            );
        }
    }

    private void validarFormato(Long formatoId) {
        if (formatoId == null) {
            throw new IllegalArgumentException(
                    "El análisis debe tener un formato asociado."
            );
        }
    }

    private void validarPrioridad(Prioridad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException(
                    "La prioridad del análisis es obligatoria."
            );
        }
    }


}
