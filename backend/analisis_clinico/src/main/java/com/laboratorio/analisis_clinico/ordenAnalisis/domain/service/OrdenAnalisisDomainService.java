package com.laboratorio.analisis_clinico.ordenAnalisis.domain.service;

import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;

import java.time.LocalDateTime;

public class OrdenAnalisisDomainService {

    /**
     * Agrega un análisis a una orden.
     * Aplica reglas de flujo, disponibilidad y coherencia inicial.
     */
    public OrdenAnalisis agregarAnalisisAOrden(
            Orden orden,
            FormatoAnalisis formato,
            Prioridad prioridad,
            LocalDateTime fechaProgramada
    ) {

        if (orden == null) {
            throw new IllegalArgumentException("La orden es obligatoria.");
        }

        if (formato == null) {
            throw new IllegalArgumentException("El formato de análisis es obligatorio.");
        }

        if (!formato.estaVigente()) {
            throw new IllegalStateException("No se puede usar un formato obsoleto.");
        }

        if (orden.estaAnulada() || orden.estaFinalizada()) {
            throw new IllegalStateException(
                    "No se pueden agregar análisis a una orden anulada o finalizada."
            );
        }

        if (fechaProgramada != null && fechaProgramada.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "La fecha programada no puede ser anterior a la fecha actual."
            );
        }

        // Regla: el análisis nace PENDIENTE
        OrdenAnalisis ordenAnalisis = new OrdenAnalisis(
                orden.getIdOrden(),
                formato.getIdFormatoAnalisis(),
                prioridad != null ? prioridad : Prioridad.NORMAL
        );

        // La fecha se define solo si fue programada explícitamente
        if (fechaProgramada != null) {
            ordenAnalisis.reprogramar(fechaProgramada);
        }

        return ordenAnalisis;
    }

    /**
     * Inicia la ejecución de un análisis.
     */
    public void iniciarAnalisis(OrdenAnalisis ordenAnalisis) {
        validarOrdenAnalisis(ordenAnalisis);

        ordenAnalisis.iniciar();
    }

    /**
     * Cancela un análisis.
     */
    public void cancelarAnalisis(OrdenAnalisis ordenAnalisis) {
        validarOrdenAnalisis(ordenAnalisis);

        ordenAnalisis.cancelar();
    }

    /**
     * Cambia la prioridad de un análisis.
     */
    public void cambiarPrioridad(
            OrdenAnalisis ordenAnalisis,
            Prioridad nuevaPrioridad
    ) {
        validarOrdenAnalisis(ordenAnalisis);

        if (nuevaPrioridad == null) {
            throw new IllegalArgumentException("La nueva prioridad es obligatoria.");
        }

        ordenAnalisis.cambiarPrioridad(nuevaPrioridad);
    }

    /**
     * Reprograma la fecha de realización de un análisis.
     */
    public void reprogramarAnalisis(
            OrdenAnalisis ordenAnalisis,
            LocalDateTime nuevaFecha
    ) {
        validarOrdenAnalisis(ordenAnalisis);

        if (nuevaFecha == null) {
            throw new IllegalArgumentException("La nueva fecha es obligatoria.");
        }

        if (nuevaFecha.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "No se puede reprogramar un análisis a una fecha pasada."
            );
        }

        ordenAnalisis.reprogramar(nuevaFecha);
    }

    /**
     * Cambia el formato del análisis antes de ser ejecutado.
     */
    public void cambiarFormatoAnalisis(
            OrdenAnalisis ordenAnalisis,
            FormatoAnalisis nuevoFormato
    ) {
        validarOrdenAnalisis(ordenAnalisis);

        if (nuevoFormato == null) {
            throw new IllegalArgumentException("El nuevo formato es obligatorio.");
        }

        if (!nuevoFormato.estaVigente()) {
            throw new IllegalStateException(
                    "No se puede usar un formato obsoleto."
            );
        }

        if (!ordenAnalisis.estaPendiente()) {
            throw new IllegalStateException(
                    "Solo se puede cambiar el formato de un análisis pendiente."
            );
        }

        ordenAnalisis.cambiarFormato(nuevoFormato.getIdFormatoAnalisis());
    }

    // =========================
    // VALIDACIONES INTERNAS
    // =========================

    private void validarOrdenAnalisis(OrdenAnalisis ordenAnalisis) {
        if (ordenAnalisis == null) {
            throw new IllegalArgumentException(
                    "El análisis de la orden es obligatorio."
            );
        }
    }
}



