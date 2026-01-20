package com.laboratorio.analisis_clinico.analisis.domain;


import com.laboratorio.analisis_clinico.analisis.domain.enume.EstadoAnalisis;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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



    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EstadoAnalisis estadoAnalisis;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="area_medica_id",nullable = false)
    private AreaMedica areaMedica;

    public void setAreaMedica(AreaMedica areaMedica) {
        if (this.areaMedica != null) {
            throw new IllegalStateException(
                    "El área médica no puede ser cambiada una vez asignada."
            );
        }
        if (areaMedica == null) {
            throw new IllegalArgumentException(
                    "El análisis debe pertenecer a un área médica."
            );
        }
        this.areaMedica = areaMedica;
    }

    @OneToMany
            (mappedBy = "analisis",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<FormatoAnalisis> listFormatoAnalisis = new HashSet<>();

    public void addFormatoAnalisis(FormatoAnalisis formatoAnalisis){
        this.listFormatoAnalisis.add(formatoAnalisis);
        formatoAnalisis.asignarAnalisis(this);
    }



    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Analisis(String nombreAnalisis, String descripcion, AreaMedica areaMedica) {
        setNombreAnalisis(nombreAnalisis);
        validarAreaMedica(areaMedica);

        this.descripcion = normalizar(descripcion);
        this.areaMedica = areaMedica;
        this.estadoAnalisis = EstadoAnalisis.ACTIVO;
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

    private void validarAreaMedica(AreaMedica areaMedica) {
        if (areaMedica == null) {
            throw new IllegalArgumentException(
                    "El análisis debe estar asociado a un área médica."
            );
        }
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }


}

