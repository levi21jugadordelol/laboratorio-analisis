package com.laboratorio.analisis_clinico.paciente.domain;


import com.laboratorio.analisis_clinico.paciente.domain.enume.Sexo;
import com.laboratorio.analisis_clinico.paciente.domain.enume.TipoPaciente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "paciente")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(nullable = false, length = 100)
    private String nombrePaciente;

    @Column(nullable = false, length = 100)
    private String apellidoPaternoPaciente;

    @Column(nullable = false, length = 100)
    private String apellidoMaternoPaciente;

    @Column(nullable = false, length = 20)
    private String dni;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Sexo sexo;

    @Column(nullable = false)
    private int edad;

    @Column(length = 150)
    private String zonaProcedencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TipoPaciente tipoPaciente;

    @Column(nullable = false, length = 50)
    private String numeroHistorialClinica;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(length = 20)
    private String telefono;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Paciente(
            String nombrePaciente,
            String apellidoPaternoPaciente,
            String apellidoMaternoPaciente,
            String dni,
            Sexo sexo,
            int edad,
            TipoPaciente tipoPaciente,
            String numeroHistorialClinica,
            String zonaProcedencia,
            String telefono
    ) {
        validarNombre(nombrePaciente);
        validarApellido(apellidoPaternoPaciente, "apellido paterno");
        validarApellido(apellidoMaternoPaciente, "apellido materno");
        validarDni(dni);
        validarSexo(sexo);
        validarEdad(edad);
        validarTipoPaciente(tipoPaciente);
        validarHistorial(numeroHistorialClinica);

        this.nombrePaciente = normalizar(nombrePaciente);
        this.apellidoPaternoPaciente = normalizar(apellidoPaternoPaciente);
        this.apellidoMaternoPaciente = normalizar(apellidoMaternoPaciente);
        this.dni = dni.trim();
        this.sexo = sexo;
        this.edad = edad;
        this.tipoPaciente = tipoPaciente;
        this.numeroHistorialClinica = numeroHistorialClinica.trim();
        this.zonaProcedencia = normalizar(zonaProcedencia);
        this.telefono = normalizar(telefono);
        this.fechaRegistro = LocalDateTime.now(); // regla de creación
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    /**
     * Permite actualizar datos administrativos del paciente.
     * NO se modifica DNI ni fecha de registro.
     */
    public void actualizarDatos(
            String nombrePaciente,
            String apellidoPaternoPaciente,
            String apellidoMaternoPaciente,
            int edad,
            String zonaProcedencia,
            String telefono
    ) {
        validarNombre(nombrePaciente);
        validarApellido(apellidoPaternoPaciente, "apellido paterno");
        validarApellido(apellidoMaternoPaciente, "apellido materno");
        validarEdad(edad);

        this.nombrePaciente = normalizar(nombrePaciente);
        this.apellidoPaternoPaciente = normalizar(apellidoPaternoPaciente);
        this.apellidoMaternoPaciente = normalizar(apellidoMaternoPaciente);
        this.edad = edad;
        this.zonaProcedencia = normalizar(zonaProcedencia);
        this.telefono = normalizar(telefono);
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean esAsegurado() {
        return this.tipoPaciente == TipoPaciente.ASEGURADO
                || this.tipoPaciente == TipoPaciente.ESSALUD;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del paciente es obligatorio."
            );
        }
    }

    private void validarApellido(String apellido, String tipo) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El " + tipo + " del paciente es obligatorio."
            );
        }
    }

    private void validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El DNI del paciente es obligatorio."
            );
        }
    }

    private void validarSexo(Sexo sexo) {
        if (sexo == null) {
            throw new IllegalArgumentException(
                    "El sexo del paciente es obligatorio."
            );
        }
    }

    private void validarEdad(int edad) {
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException(
                    "La edad del paciente no es válida."
            );
        }
    }

    private void validarTipoPaciente(TipoPaciente tipoPaciente) {
        if (tipoPaciente == null) {
            throw new IllegalArgumentException(
                    "El tipo de paciente es obligatorio."
            );
        }
    }

    private void validarHistorial(String historial) {
        if (historial == null || historial.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El número de historia clínica es obligatorio."
            );
        }
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

