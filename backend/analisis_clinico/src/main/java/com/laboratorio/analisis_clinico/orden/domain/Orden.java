package com.laboratorio.analisis_clinico.orden.domain;


import com.laboratorio.analisis_clinico.orden.domain.enume.EstadoOrden;
import com.laboratorio.analisis_clinico.orden.domain.enume.TipoOrden;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orden")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    /**
     * Médico que emite la receta.
     * Obligatorio solo si el tipo es RECETA.
     */
    @Column
    private Long doctorId;

    /**
     * Toda orden pertenece a un paciente.
     */
    @Column(nullable = false)
    private Long pacienteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TipoOrden tipoOrden;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoOrden estadoOrden;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Long createdByUsuario;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Orden(
            Long pacienteId,
            TipoOrden tipoOrden,
            Long doctorId,
            Long createdByUsuario
    ) {
        validarPaciente(pacienteId);
        validarTipoOrden(tipoOrden);
        validarDoctorSegunTipo(tipoOrden, doctorId);
        validarUsuario(createdByUsuario);

        this.pacienteId = pacienteId;
        this.tipoOrden = tipoOrden;
        this.doctorId = doctorId;
        this.createdByUsuario = createdByUsuario;

        this.estadoOrden = EstadoOrden.CREADA; // regla de creación
        this.fechaCreacion = LocalDateTime.now();
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void iniciarProceso() {
        if (this.estadoOrden != EstadoOrden.CREADA) {
            throw new IllegalStateException(
                    "Solo una orden creada puede pasar a EN_PROCESO."
            );
        }
        this.estadoOrden = EstadoOrden.EN_PROCESO;
    }

    public void finalizar() {
        if (this.estadoOrden != EstadoOrden.EN_PROCESO) {
            throw new IllegalStateException(
                    "Solo una orden en proceso puede finalizarse."
            );
        }
        this.estadoOrden = EstadoOrden.FINALIZADA;
    }

    public void anular() {
        if (this.estadoOrden == EstadoOrden.FINALIZADA) {
            throw new IllegalStateException(
                    "Una orden finalizada no puede anularse."
            );
        }
        if (this.estadoOrden == EstadoOrden.ANULADA) {
            throw new IllegalStateException(
                    "La orden ya se encuentra anulada."
            );
        }
        this.estadoOrden = EstadoOrden.ANULADA;
    }

    /**
     * Permite cambiar el médico solo mientras la orden esté creada.
     */
    public void cambiarDoctor(Long nuevoDoctorId) {
        if (this.estadoOrden != EstadoOrden.CREADA) {
            throw new IllegalStateException(
                    "No se puede modificar el médico de una orden en proceso."
            );
        }
        validarDoctorSegunTipo(this.tipoOrden, nuevoDoctorId);
        this.doctorId = nuevoDoctorId;
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaCreada() {
        return this.estadoOrden == EstadoOrden.CREADA;
    }

    public boolean estaFinalizada() {
        return this.estadoOrden == EstadoOrden.FINALIZADA;
    }

    public boolean estaAnulada() {
        return this.estadoOrden == EstadoOrden.ANULADA;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarPaciente(Long pacienteId) {
        if (pacienteId == null) {
            throw new IllegalArgumentException(
                    "La orden debe estar asociada a un paciente."
            );
        }
    }

    private void validarTipoOrden(TipoOrden tipoOrden) {
        if (tipoOrden == null) {
            throw new IllegalArgumentException(
                    "El tipo de orden es obligatorio."
            );
        }
    }

    private void validarDoctorSegunTipo(TipoOrden tipoOrden, Long doctorId) {
        if (tipoOrden == TipoOrden.RECETA && doctorId == null) {
            throw new IllegalArgumentException(
                    "Una orden por receta requiere médico."
            );
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "El usuario creador de la orden es obligatorio."
            );
        }
    }
}

