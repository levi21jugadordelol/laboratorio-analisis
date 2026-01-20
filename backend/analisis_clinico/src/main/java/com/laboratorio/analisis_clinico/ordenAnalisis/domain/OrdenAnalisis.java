package com.laboratorio.analisis_clinico.ordenAnalisis.domain;


import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orden_analisis")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdenAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenAnalisis;



    /**
     * Fecha en que el análisis empieza a ejecutarse.
     * Se asigna al iniciar el proceso.
     */
    private LocalDateTime fechaRealizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoOrdenMedicaAnalisis estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Prioridad prioridad;

    @ManyToOne
    @JoinColumn(name="orden_id",nullable = false)
    private Orden orden;

    public void asignarOrden(Orden orden) {
        if (this.orden != null) {
            throw new IllegalStateException(
                    "El análisis ya está asociado a una orden."
            );
        }
        if (orden == null) {
            throw new IllegalArgumentException(
                    "El análisis debe pertenecer a una orden."
            );
        }
        this.orden = orden;
    }

    @ManyToOne
    @JoinColumn(name="formato_id",nullable = false)
    private FormatoAnalisis formatoAnalisis;

    public void asignarFormato(FormatoAnalisis formatoAnalisis){
         if(this.formatoAnalisis != null){
             throw new IllegalStateException(
                     "El análisis ya está asociado a una formato."
             );
         }

        if(formatoAnalisis == null){
            throw new IllegalStateException(
                    "El análisis debe pertenecer a un formato."
            );
        }

        this.formatoAnalisis=formatoAnalisis;
    }

    @OneToOne(mappedBy = "ordenAnalisis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Resultado resultado;


    public void asignarResultado(Resultado resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("El resultado no puede ser nulo.");
        }
        if (this.resultado != null) {
            throw new IllegalStateException("La orden ya tiene un resultado.");
        }
        this.resultado = resultado;
        resultado.asignarOrdenAnalisis(this);
    }


    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public OrdenAnalisis(Prioridad prioridad) {
        validarPrioridad(prioridad);
        this.prioridad = prioridad;
        this.estado = EstadoOrdenMedicaAnalisis.PENDIENTE;
    }


    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void iniciar() {
        if (this.estado != EstadoOrdenMedicaAnalisis.PENDIENTE) {
            throw new IllegalStateException(
                    "Solo un análisis pendiente puede iniciarse."
            );
        }
        this.estado = EstadoOrdenMedicaAnalisis.EN_PROCESO;
        this.fechaRealizacion = LocalDateTime.now();
    }

    public void completar() {
        if (this.estado != EstadoOrdenMedicaAnalisis.EN_PROCESO) {
            throw new IllegalStateException(
                    "Solo un análisis en proceso puede completarse."
            );
        }
        this.estado = EstadoOrdenMedicaAnalisis.COMPLETADO;
    }

    public void cancelar() {
        if (this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO) {
            throw new IllegalStateException(
                    "Un análisis completado no puede cancelarse."
            );
        }
        if (this.estado == EstadoOrdenMedicaAnalisis.CANCELADO) {
            throw new IllegalStateException(
                    "El análisis ya se encuentra cancelado."
            );
        }
        this.estado = EstadoOrdenMedicaAnalisis.CANCELADO;
    }

    public void cambiarPrioridad(Prioridad nuevaPrioridad) {
        if (this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO) {
            throw new IllegalStateException(
                    "No se puede cambiar la prioridad de un análisis completado."
            );
        }
        validarPrioridad(nuevaPrioridad);
        this.prioridad = nuevaPrioridad;
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaPendiente() {
        return this.estado == EstadoOrdenMedicaAnalisis.PENDIENTE;
    }

    public boolean estaEnProceso() {
        return this.estado == EstadoOrdenMedicaAnalisis.EN_PROCESO;
    }

    public boolean estaCompletado() {
        return this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO;
    }

    public void reprogramar(LocalDateTime nuevaFecha) {
        if (this.estado == EstadoOrdenMedicaAnalisis.COMPLETADO) {
            throw new IllegalStateException(
                    "No se puede reprogramar un análisis completado."
            );
        }

        if (this.estado == EstadoOrdenMedicaAnalisis.CANCELADO) {
            throw new IllegalStateException(
                    "No se puede reprogramar un análisis cancelado."
            );
        }

        if (nuevaFecha == null) {
            throw new IllegalArgumentException(
                    "La nueva fecha de realización es obligatoria."
            );
        }

        if (nuevaFecha.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "La nueva fecha de realización no puede ser pasada."
            );
        }

        this.fechaRealizacion = nuevaFecha;
    }

    public void cambiarFormato(FormatoAnalisis nuevoFormato) {
        if (this.estado != EstadoOrdenMedicaAnalisis.PENDIENTE) {
            throw new IllegalStateException(
                    "Solo se puede cambiar el formato de un análisis pendiente."
            );
        }
        if (nuevoFormato == null) {
            throw new IllegalArgumentException(
                    "El nuevo formato es obligatorio."
            );
        }
        this.formatoAnalisis = nuevoFormato;
    }




    // =========================
    // INVARIANTES INTERNAS
    // =========================


    private void validarPrioridad(Prioridad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException(
                    "La prioridad del análisis es obligatoria."
            );
        }
    }


}
