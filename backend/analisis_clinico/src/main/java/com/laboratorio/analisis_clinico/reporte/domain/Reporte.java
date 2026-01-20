package com.laboratorio.analisis_clinico.reporte.domain;

import com.laboratorio.analisis_clinico.reporte.domain.enume.TipoReporte;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoReporte tipoReporte;

    /**
     * Representa el período del reporte (ej: 2024-01, Q1-2024, 01-15/01-30).
     * El dominio solo exige que exista.
     */
    @Column(nullable = false, length = 50)
    private String periodo;

    /**
     * Fecha exacta de generación del reporte.
     * Inmutable.
     */
    @Column(nullable = false)
    private LocalDateTime fechaGenerada;

    /**
     * Ubicación del archivo generado (PDF, Excel, etc).
     */
    @Column(nullable = false, length = 255)
    private String archivoUrl;

    /**
     * Indica si el reporte debe enviarse automáticamente por correo.
     */
    @Column(nullable = false)
    private boolean envioAutomaticoCorreo;

    /**
     * Usuario responsable de la generación.
     */
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdByUsuario;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;


    public void asignarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException(
                    "El usuario no puede ser nulo."
            );
        }
        if (this.usuario != null) {
            throw new IllegalStateException(
                    "El reporte ya tiene un usuario asignado."
            );
        }
        this.usuario = usuario;
    }


    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Reporte(
            TipoReporte tipoReporte,
            String periodo,
            String archivoUrl,
            boolean envioAutomaticoCorreo,
            Long createdByUsuario
    ) {
        validarTipo(tipoReporte);
        validarPeriodo(periodo);
        validarArchivo(archivoUrl);
        validarUsuario(createdByUsuario);

        this.tipoReporte = tipoReporte;
        this.periodo = periodo.trim();
        this.archivoUrl = archivoUrl.trim();
        this.envioAutomaticoCorreo = envioAutomaticoCorreo;
        this.createdByUsuario = createdByUsuario;
        this.fechaGenerada = LocalDateTime.now(); // regla de creación
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean esAutomatico() {
        return envioAutomaticoCorreo;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarTipo(TipoReporte tipoReporte) {
        if (tipoReporte == null) {
            throw new IllegalArgumentException(
                    "El tipo de reporte es obligatorio."
            );
        }
    }

    private void validarPeriodo(String periodo) {
        if (periodo == null || periodo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El período del reporte es obligatorio."
            );
        }
    }

    private void validarArchivo(String archivoUrl) {
        if (archivoUrl == null || archivoUrl.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La ubicación del archivo del reporte es obligatoria."
            );
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "El usuario generador del reporte es obligatorio."
            );
        }
    }


}

