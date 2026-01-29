package com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.dto.OrdenAnalisisDtoRequest;
import com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.dto.OrdenAnalisisDtoResponse;
import com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.mapper.OrdenAnalisisWebMapper;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.Queries.ListarAnalisisPendientes;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.Queries.ListarAnalisisUrgentes;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.Queries.VerCambiosEstadoAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.commands.*;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ordenes-analisis")
@RequiredArgsConstructor
public class OrdenAnalisisController {

    private final AgregarAnalisisAOrden agregarAnalisisAOrden;
    private final CambiarPrioridadAnalisis cambiarPrioridadAnalisis;
    private final CancelarAnalisis cancelarAnalisis;
    private final IniciarAnalisis iniciarAnalisis;
    private final ReprogramarAnalisis reprogramarAnalisis;
    private final ListarAnalisisPendientes listarAnalisisPendientes;
    private final ListarAnalisisUrgentes listarAnalisisUrgentes;
    private final VerCambiosEstadoAnalisis verCambiosEstadoAnalisis;

    private final OrdenAnalisisWebMapper mapper;

    // =========================
    // CREAR (agregar análisis a orden)
    // =========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarAnalisis(
            @Valid @RequestBody OrdenAnalisisDtoRequest request
    ) {
        agregarAnalisisAOrden.ejecutar(
                request.getOrdenId(),
                request.getFormatoId(),
                request.getPrioridad(),
                null // fecha programada (si luego la agregas al DTO)
        );
    }

    // =========================
    // CAMBIAR PRIORIDAD
    // =========================
    @PatchMapping("/{id}/prioridad")
    public void cambiarPrioridad(
            @PathVariable Long id,
            @RequestParam Prioridad prioridad
    ) {
        cambiarPrioridadAnalisis.ejecutar(id, prioridad);
    }

    // =========================
    // INICIAR ANÁLISIS
    // =========================
    @PatchMapping("/{id}/iniciar")
    public void iniciar(@PathVariable Long id) {
        iniciarAnalisis.ejecutar(id);
    }

    // =========================
    // CANCELAR ANÁLISIS
    // =========================
    @PatchMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Long id) {
        cancelarAnalisis.ejecutar(id);
    }

    // =========================
    // REPROGRAMAR
    // =========================
    @PatchMapping("/{id}/reprogramar")
    public void reprogramar(
            @PathVariable Long id,
            @RequestParam LocalDateTime nuevaFecha
    ) {
        reprogramarAnalisis.ejecutar(id, nuevaFecha);
    }

    // =========================
    // LISTAR PENDIENTES
    // =========================
    @GetMapping("/pendientes")
    public List<OrdenAnalisisDtoResponse> listarPendientes() {
        return listarAnalisisPendientes.ejecutar().stream()
                .map(mapper::toResponse)
                .toList();
    }

    // =========================
    // LISTAR URGENTES
    // =========================
    @GetMapping("/urgentes")
    public List<OrdenAnalisisDtoResponse> listarUrgentes() {
        return listarAnalisisUrgentes.ejecutar().stream()
                .map(mapper::toResponse)
                .toList();
    }

    // =========================
    // HISTORIAL DE CAMBIOS
    // =========================
    @GetMapping("/{id}/historial")
    public List<AuditLog> verHistorial(@PathVariable Long id) {
        return verCambiosEstadoAnalisis.ejecutar(id);
    }
}

