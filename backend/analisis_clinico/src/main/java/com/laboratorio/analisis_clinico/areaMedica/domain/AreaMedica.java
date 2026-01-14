package com.laboratorio.analisis_clinico.areaMedica.domain;

import com.laboratorio.analisis_clinico.areaMedica.domain.enume.EstadoAreaMedica;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "area_medica")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArea;

    // Detalle de persistencia (no es regla de dominio)
    @Column(nullable = false, length = 100)
    private String nombreArea;

    @Column(length = 255)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EstadoAreaMedica estadoAreaMedica;

    /**
     * Constructor de dominio: asegura invariantes desde el nacimiento.
     */
    public AreaMedica(String nombreArea, String descripcion) {
        setNombreArea(nombreArea);           // regla de dominio
        this.descripcion = normalizar(descripcion);
        this.estadoAreaMedica = EstadoAreaMedica.ACTIVA; // regla de creación
    }

    // =========================
    // Reglas de dominio
    // =========================

    public void actualizarDatos(String nombreArea, String descripcion) {
        setNombreArea(nombreArea);           // regla de dominio
        this.descripcion = normalizar(descripcion);
    }

    public void activar() {
        if (this.estadoAreaMedica == EstadoAreaMedica.ACTIVA) {
            throw new IllegalStateException("El área médica ya está activa.");
        }
        this.estadoAreaMedica = EstadoAreaMedica.ACTIVA;
    }

    public void desactivar() {
        if (this.estadoAreaMedica == EstadoAreaMedica.INACTIVA) {
            throw new IllegalStateException("El área médica ya está inactiva.");
        }
        this.estadoAreaMedica = EstadoAreaMedica.INACTIVA;
    }

    /**
     * “Eliminar” no existe en el dominio: se desactiva.
     * (Si alguien intenta borrar, tu caso de uso debería llamar a desactivar()).
     */
    public void eliminarLogicamente() {
        desactivar();
    }

    // =========================
    // Consultas de dominio
    // =========================

    public boolean estaActiva() {
        return this.estadoAreaMedica == EstadoAreaMedica.ACTIVA;
    }

    // =========================
    // Invariantes internas
    // =========================

    private void setNombreArea(String nombreArea) {
        String n = normalizar(nombreArea);

        if (n == null || n.isBlank()) {
            throw new IllegalArgumentException("El nombre del área médica es obligatorio.");
        }
        // Regla de dominio simple: el nombre debe ser razonable
        if (n.length() > 100) {
            throw new IllegalArgumentException("El nombre del área médica excede el máximo permitido (100).");
        }

        this.nombreArea = n;
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}
