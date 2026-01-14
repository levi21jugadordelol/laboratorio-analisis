package com.laboratorio.analisis_clinico.formatoAnalisis.domain;


import com.laboratorio.analisis_clinico.formatoAnalisis.domain.enume.EstadoFormato;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "formato_analisis")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FormatoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormatoAnalisis;

    /**
     * A nivel de dominio: un formato SIEMPRE pertenece a un análisis.
     * La validación de existencia del análisis se hace fuera.
     */
    @Column(nullable = false)
    private Long analisisId;

    @Column(nullable = false, length = 150)
    private String nombreFormato;

    @Column(length = 255)
    private String descripcion;

    /**
     * Define la estructura clínica del resultado.
     * El dominio no valida su esquema, solo exige que exista.
     */
    @Column(nullable = false, columnDefinition = "json")
    private Map<String, Object> estructuraFormato;

    /**
     * Versionado del formato.
     * Controlado por el dominio.
     */
    @Column(nullable = false)
    private double version;

    /**
     * Usuario responsable de la creación.
     */
    @Column(nullable = false)
    private Long createdByUsuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoFormato estadoFormato;

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public FormatoAnalisis(
            Long analisisId,
            String nombreFormato,
            String descripcion,
            Map<String, Object> estructuraFormato,
            Long createdByUsuario
    ) {
        validarAnalisis(analisisId);
        setNombreFormato(nombreFormato);
        validarEstructura(estructuraFormato);
        validarUsuario(createdByUsuario);

        this.analisisId = analisisId;
        this.descripcion = normalizar(descripcion);
        this.estructuraFormato = estructuraFormato;
        this.createdByUsuario = createdByUsuario;
        this.version = 1.0; // regla de creación
        this.estadoFormato = EstadoFormato.VIGENTE; // regla de creación
        this.fechaCreacion = LocalDateTime.now();
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    /**
     * Un formato vigente puede volverse obsoleto,
     * pero nunca se elimina.
     */
    public void marcarComoObsoleto() {
        if (this.estadoFormato == EstadoFormato.OBSOLETO) {
            throw new IllegalStateException(
                    "El formato ya se encuentra obsoleto."
            );
        }
        this.estadoFormato = EstadoFormato.OBSOLETO;
    }

    /**
     * Crea una nueva versión del formato.
     * El formato actual queda obsoleto.
     */
    public FormatoAnalisis crearNuevaVersion(
            Map<String, Object> nuevaEstructura,
            Long usuarioId
    ) {
        if (this.estadoFormato != EstadoFormato.VIGENTE) {
            throw new IllegalStateException(
                    "Solo se puede versionar un formato vigente."
            );
        }

        validarEstructura(nuevaEstructura);
        validarUsuario(usuarioId);

        this.marcarComoObsoleto();

        return new FormatoAnalisis(
                this.analisisId,
                this.nombreFormato,
                this.descripcion,
                nuevaEstructura,
                usuarioId
        ).conVersion(this.version + 1);
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaVigente() {
        return this.estadoFormato == EstadoFormato.VIGENTE;
    }

    public boolean estaObsoleto() {
        return this.estadoFormato == EstadoFormato.OBSOLETO;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void setNombreFormato(String nombreFormato) {
        String n = normalizar(nombreFormato);

        if (n == null || n.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del formato es obligatorio."
            );
        }

        if (n.length() > 150) {
            throw new IllegalArgumentException(
                    "El nombre del formato excede el máximo permitido (150)."
            );
        }

        this.nombreFormato = n;
    }

    private void validarAnalisis(Long analisisId) {
        if (analisisId == null) {
            throw new IllegalArgumentException(
                    "El formato debe estar asociado a un análisis."
            );
        }
    }

    private void validarEstructura(Map<String, Object> estructura) {
        if (estructura == null || estructura.isEmpty()) {
            throw new IllegalArgumentException(
                    "La estructura del formato es obligatoria."
            );
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "El usuario creador del formato es obligatorio."
            );
        }
    }

    private FormatoAnalisis conVersion(double nuevaVersion) {
        this.version = nuevaVersion;
        return this;
    }
    public void actualizarDatos(String nombreFormato, String descripcion) {
        setNombreFormato(nombreFormato);
        this.descripcion = normalizar(descripcion);
    }


    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

