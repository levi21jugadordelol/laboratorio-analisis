package com.laboratorio.analisis_clinico.resultado.domain;

import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "resultado")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultado;


    /**
     * Contenido clínico del resultado.
     */
    @Column(nullable = false, columnDefinition = "json")
    private Map<String, Object> resultadoJson;

    /**
     * Fecha en que se registró el resultado.
     * Inmutable.
     */
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Observaciones clínicas adicionales.
     */
    @Column(length = 255)
    private String observacion;

    /**
     * Usuario responsable de la creación/modificación.
     */
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdByUsuario;

    /**
     * Versionado clínico del resultado.
     */
    @Column(nullable = false)
    private double version;

    /**
     * Indica si el resultado fue validado clínicamente.
     */
    @Column(nullable = false)
    private boolean validado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_analisis_id", nullable = false, unique = true)
    private OrdenAnalisis ordenAnalisis;

    public void asignarOrdenAnalisis(OrdenAnalisis ordenAnalisis) {
        if (this.ordenAnalisis != null) {
            throw new IllegalStateException(
                    "El resultado ya está asociado a una orden."
            );
        }
        this.ordenAnalisis = ordenAnalisis;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    public void asignarUsuario(Usuario usuario) {
        if(usuario == null){
          throw new  IllegalArgumentException("el resultado no puede ser nullo");
        }
        if(this.usuario != null){
            throw new IllegalStateException(
                    "La resultado ya está asociada a un usuario."
            );
        }
        this.usuario=usuario;
    }




    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Resultado(
            OrdenAnalisis ordenAnalisis,
            Map<String, Object> resultadoJson,
            String observacion,
            Long createdByUsuario
    ) {
        validarOrdenAnalisis(ordenAnalisis);
        validarResultado(resultadoJson);
        validarUsuario(createdByUsuario);

        this.ordenAnalisis = ordenAnalisis;
        this.resultadoJson = resultadoJson;
        this.observacion = normalizar(observacion);
        this.createdByUsuario = createdByUsuario;
        this.fechaRegistro = LocalDateTime.now();
        this.version = 1.0;
        this.validado = false;
    }


    // =========================
    // REGLAS DE DOMINIO
    // =========================

    /**
     * Valida clínicamente el resultado.
     * Acción irreversible.
     */
    public void validar() {
        if (this.validado) {
            throw new IllegalStateException(
                    "El resultado ya fue validado."
            );
        }
        this.validado = true;
    }

    /**
     * Crea una nueva versión del resultado.
     * El resultado actual debe NO estar validado.
     */
    public Resultado crearNuevaVersion(
            Map<String, Object> nuevoResultado,
            String motivo,
            Long usuarioId
    ) {
        if (this.validado) {
            throw new IllegalStateException(
                    "No se puede modificar un resultado validado."
            );
        }

        validarResultado(nuevoResultado);
        validarMotivo(motivo);
        validarUsuario(usuarioId);

        return new Resultado(
                this.ordenAnalisis,
                nuevoResultado,
                motivo,
                usuarioId
        ).conVersion(this.version + 1);
    }


    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaValidado() {
        return this.validado;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarOrdenAnalisis(OrdenAnalisis ordenAnalisis) {
        if (ordenAnalisis == null) {
            throw new IllegalArgumentException(
                    "El resultado debe estar asociado a una orden de análisis."
            );
        }
    }


    private void validarResultado(Map<String, Object> resultado) {
        if (resultado == null || resultado.isEmpty()) {
            throw new IllegalArgumentException(
                    "El resultado clínico no puede estar vacío."
            );
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "El usuario responsable del resultado es obligatorio."
            );
        }
    }

    private void validarMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La modificación del resultado requiere un motivo."
            );
        }
    }

    private Resultado conVersion(double nuevaVersion) {
        this.version = nuevaVersion;
        return this;
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }


}

