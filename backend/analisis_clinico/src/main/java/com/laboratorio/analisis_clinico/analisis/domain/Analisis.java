package com.laboratorio.analisis_clinico.analisis.domain;


import com.laboratorio.analisis_clinico.analisis.domain.enume.EstadoAnalisis;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "analisis")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnalisis;

    @Column(nullable = false, length = 150)
    private String nombreAnalisis;

    @Column(length = 255)
    private String descripcion;

    /**
     * Referencia al área médica.
     * A nivel de dominio: un análisis SIEMPRE pertenece a un área.
     * La validación de existencia se hace fuera (use case / domain service).
     */
    @Column(nullable = false)
    private Long areaMedicaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EstadoAnalisis estadoAnalisis;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Analisis(String nombreAnalisis, String descripcion, Long areaMedicaId) {
        setNombreAnalisis(nombreAnalisis);
        validarAreaMedica(areaMedicaId);

        this.descripcion = normalizar(descripcion);
        this.areaMedicaId = areaMedicaId;
        this.estadoAnalisis = EstadoAnalisis.ACTIVO; // regla de creación
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void actualizarDatos(String nombreAnalisis, String descripcion) {
        setNombreAnalisis(nombreAnalisis);
        this.descripcion = normalizar(descripcion);
    }

    public void activar() {
        if (this.estadoAnalisis == EstadoAnalisis.ACTIVO) {
            throw new IllegalStateException(
                    "El análisis ya se encuentra activo."
            );
        }
        this.estadoAnalisis = EstadoAnalisis.ACTIVO;
    }

    public void inactivar() {
        if (this.estadoAnalisis == EstadoAnalisis.INACTIVO) {
            throw new IllegalStateException(
                    "El análisis ya se encuentra inactivo."
            );
        }
        this.estadoAnalisis = EstadoAnalisis.INACTIVO;
    }

    /**
     * Eliminar no existe en el dominio: se inactiva.
     */
    public void eliminarLogicamente() {
        inactivar();
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaActivo() {
        return this.estadoAnalisis == EstadoAnalisis.ACTIVO;
    }

    public boolean estaInactivo() {
        return this.estadoAnalisis == EstadoAnalisis.INACTIVO;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void setNombreAnalisis(String nombreAnalisis) {
        String n = normalizar(nombreAnalisis);

        if (n == null || n.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del análisis es obligatorio."
            );
        }

        if (n.length() > 150) {
            throw new IllegalArgumentException(
                    "El nombre del análisis excede el máximo permitido (150)."
            );
        }

        this.nombreAnalisis = n;
    }

    private void validarAreaMedica(Long areaMedicaId) {
        if (areaMedicaId == null) {
            throw new IllegalArgumentException(
                    "El análisis debe estar asociado a un área médica."
            );
        }
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

