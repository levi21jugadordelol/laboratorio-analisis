package com.laboratorio.analisis_clinico.resultado.domain.service;

import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;

import java.util.Map;

public class ResultadoDomainService {

    /**
     * Registra el resultado inicial cuando el análisis termina.
     * El resultado nace PENDIENTE (no validado).
     */
    public Resultado registrarResultadoInicial(
            OrdenAnalisis ordenAnalisis,
            Map<String, Object> resultadoJson,
            String observacion,
            Long usuarioId
    ) {

        if (ordenAnalisis == null) {
            throw new IllegalArgumentException(
                    "El análisis es obligatorio para registrar un resultado."
            );
        }

        if (!ordenAnalisis.estaCompletado()) {
            throw new IllegalStateException(
                    "Solo se puede registrar un resultado cuando el análisis está completado."
            );
        }

        return new Resultado(
                ordenAnalisis.getIdOrdenAnalisis(),
                resultadoJson,
                observacion,
                usuarioId
        );
    }

    /**
     * Valida clínicamente un resultado.
     * Acción irreversible.
     */
    public void validarResultado(Resultado resultado) {
        validarResultadoExiste(resultado);

        resultado.validar();
    }

    /**
     * Reabre un resultado validado con un motivo obligatorio.
     * No lo modifica: prepara el contexto para corrección.
     */
    public void reabrirResultadoConMotivo(
            Resultado resultado,
            String motivo
    ) {
        validarResultadoExiste(resultado);

        if (!resultado.estaValidado()) {
            throw new IllegalStateException(
                    "Solo se puede reabrir un resultado validado."
            );
        }

        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La reapertura del resultado requiere un motivo."
            );
        }

        // Nota: no cambia estado aquí
        // El efecto real es permitir crear una nueva versión
        // La trazabilidad se registra vía AuditLog desde el use case
    }

    /**
     * Corrige un resultado creando una nueva versión.
     * No sobrescribe el resultado validado.
     */
    public Resultado corregirResultadoCreandoNuevaVersion(
            Resultado resultadoActual,
            Map<String, Object> nuevoResultado,
            String motivo,
            Long usuarioId
    ) {
        validarResultadoExiste(resultadoActual);

        if (!resultadoActual.estaValidado()) {
            throw new IllegalStateException(
                    "Solo se puede corregir un resultado previamente validado."
            );
        }

        return resultadoActual.crearNuevaVersion(
                nuevoResultado,
                motivo,
                usuarioId
        );
    }

    // =========================
    // VALIDACIONES INTERNAS
    // =========================

    private void validarResultadoExiste(Resultado resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException(
                    "El resultado es obligatorio."
            );
        }
    }
}

