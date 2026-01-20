package com.laboratorio.analisis_clinico.orden.domain;


import com.laboratorio.analisis_clinico.orden.domain.enume.EstadoOrden;
import com.laboratorio.analisis_clinico.orden.domain.enume.TipoOrden;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orden")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TipoOrden tipoOrden;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoOrden estadoOrden;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdByUsuario; // auditoría

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    public void asignarUsuario(Usuario usuario){
        if (usuario == null) {
            throw new IllegalArgumentException(
                    "El usuario no puede ser nulo."
            );
        }
        if (this.usuario != null) {
            throw new IllegalStateException(
                    "La orden ya está asociada a un usuario."
            );
        }
        this.usuario = usuario;
    }


    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Orden(

            TipoOrden tipoOrden,

            Long createdByUsuario
    ) {

        validarTipoOrden(tipoOrden);
        validarDoctorSegunTipo(tipoOrden);
        validarUsuario(createdByUsuario);


        this.tipoOrden = tipoOrden;

        this.createdByUsuario = createdByUsuario;

        this.estadoOrden = EstadoOrden.CREADA; // regla de creación
        this.fechaCreacion = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name="paciente_id",nullable = false)
    private Paciente paciente;

    public void asignarPaciente(Paciente paciente) {
        if (this.paciente != null) {
            throw new IllegalStateException(
                    "La orden ya está asociada a un paciente."
            );
        }
        if (paciente == null) {
            throw new IllegalArgumentException(
                    "La orden debe pertenecer a un paciente."
            );
        }
        this.paciente = paciente;
    }

    @ManyToOne
    @JoinColumn(name="personal_medico_id",nullable = false)
    private PersonalMedico personalMedico;

    public void asignarPersonal(PersonalMedico personalMedico) {
        if (this.personalMedico != null) {
            throw new IllegalStateException(
                    "La orden ya está asociada a un personal médico."
            );
        }
        if (personalMedico == null) {
            throw new IllegalArgumentException(
                    "La orden debe estar asociada a un personal médico."
            );
        }
        this.personalMedico = personalMedico;
    }

    @OneToMany
            (mappedBy = "orden",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<OrdenAnalisis> listOrdenAnalisis = new HashSet<>();

    public void addOrdenAnalisis(OrdenAnalisis ordenAnalisis){
        this.listOrdenAnalisis.add(ordenAnalisis);
        ordenAnalisis.asignarOrden(this);
    }



    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void iniciarProceso() {
        validarOrdenCompleta();

        if (this.estadoOrden != EstadoOrden.CREADA) {
            throw new IllegalStateException(
                    "Solo una orden creada puede pasar a EN_PROCESO."
            );
        }
        this.estadoOrden = EstadoOrden.EN_PROCESO;
    }

    private void validarOrdenCompleta() {
        if (this.tipoOrden == TipoOrden.RECETA && this.personalMedico == null) {
            throw new IllegalStateException(
                    "Una orden por receta debe tener un médico asignado."
            );
        }
        if (this.paciente == null) {
            throw new IllegalStateException(
                    "La orden debe tener un paciente asignado."
            );
        }
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
    public void cambiarPersonalMedico(PersonalMedico nuevoPersonalMedico) {
        if (this.estadoOrden != EstadoOrden.CREADA) {
            throw new IllegalStateException(
                    "No se puede modificar el personal médico de una orden en proceso."
            );
        }
        if (nuevoPersonalMedico == null) {
            throw new IllegalArgumentException(
                    "La orden debe estar asociada a un personal médico."
            );
        }
        this.personalMedico = nuevoPersonalMedico;
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



    private void validarTipoOrden(TipoOrden tipoOrden) {
        if (tipoOrden == null) {
            throw new IllegalArgumentException(
                    "El tipo de orden es obligatorio."
            );
        }
    }

    private void validarDoctorSegunTipo(TipoOrden tipoOrden) {
        if (tipoOrden == TipoOrden.RECETA ) {
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

