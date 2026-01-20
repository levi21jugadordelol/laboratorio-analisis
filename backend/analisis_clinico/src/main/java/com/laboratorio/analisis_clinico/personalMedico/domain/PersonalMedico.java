package com.laboratorio.analisis_clinico.personalMedico.domain;

import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.personalMedico.domain.enume.EstadoPersonalMedico;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personal_medico")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonalMedico;

    @Column(nullable = false, length = 100)
    private String nombrePersonalMedico;

    @Column(nullable = false, length = 100)
    private String apellidoPersonalMedico;

    @Column(nullable = false, length = 100)
    private String especialidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EstadoPersonalMedico estadoPersonalMedico;

    @OneToMany
            (mappedBy = "personalMedico",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orden> listaOrdenes = new HashSet<>();

    public void addOrdenes(Orden orden){
        this.listaOrdenes.add(orden);
        orden.asignarPersonal(this);
    }

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public PersonalMedico(
            String nombrePersonalMedico,
            String apellidoPersonalMedico,
            String especialidad
    ) {
        validarNombre(nombrePersonalMedico);
        validarApellido(apellidoPersonalMedico);
        validarEspecialidad(especialidad);

        this.nombrePersonalMedico = normalizar(nombrePersonalMedico);
        this.apellidoPersonalMedico = normalizar(apellidoPersonalMedico);
        this.especialidad = normalizar(especialidad);
        this.estadoPersonalMedico = EstadoPersonalMedico.ACTIVO; // regla de creación
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void actualizarDatos(
            String nombrePersonalMedico,
            String apellidoPersonalMedico,
            String especialidad
    ) {
        validarNombre(nombrePersonalMedico);
        validarApellido(apellidoPersonalMedico);
        validarEspecialidad(especialidad);

        this.nombrePersonalMedico = normalizar(nombrePersonalMedico);
        this.apellidoPersonalMedico = normalizar(apellidoPersonalMedico);
        this.especialidad = normalizar(especialidad);
    }

    public void activar() {
        if (this.estadoPersonalMedico == EstadoPersonalMedico.ACTIVO) {
            throw new IllegalStateException(
                    "El médico ya se encuentra activo."
            );
        }
        this.estadoPersonalMedico = EstadoPersonalMedico.ACTIVO;
    }

    public void desactivar() {
        if (this.estadoPersonalMedico == EstadoPersonalMedico.INACTIVO) {
            throw new IllegalStateException(
                    "El médico ya se encuentra inactivo."
            );
        }
        this.estadoPersonalMedico = EstadoPersonalMedico.INACTIVO;
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaActivo() {
        return this.estadoPersonalMedico == EstadoPersonalMedico.ACTIVO;
    }

    public boolean estaInactivo() {
        return this.estadoPersonalMedico == EstadoPersonalMedico.INACTIVO;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del médico es obligatorio."
            );
        }
    }

    private void validarApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El apellido del médico es obligatorio."
            );
        }
    }

    private void validarEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La especialidad del médico es obligatoria."
            );
        }
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

