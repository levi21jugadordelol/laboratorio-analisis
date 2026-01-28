package com.laboratorio.analisis_clinico.auditlog.adapter.in.web;

import com.laboratorio.analisis_clinico.auditlog.adapter.in.web.dto.AuditLogDtoResponse;
import com.laboratorio.analisis_clinico.auditlog.adapter.in.web.mapper.AuditLogMapper;
import com.laboratorio.analisis_clinico.auditlog.application.usecase.Queries.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditlogController {

    private final ConsultarAccionesCriticas consultarAccionesCriticas;
    private final ConsultarCambiosPorUsuario consultarCambiosPorUsuario;
    private final ConsultarHistorialCambiosPorEntidad consultarPorEntidad;
    private final VerCambiosYValidacionesResultados verCambiosResultados;
    private final VerHistorialCompletoPaciente verHistorialPaciente;
    private final AuditLogMapper mapper;

    public AuditlogController(
            ConsultarAccionesCriticas consultarAccionesCriticas,
            ConsultarCambiosPorUsuario consultarCambiosPorUsuario,
            ConsultarHistorialCambiosPorEntidad consultarPorEntidad,
            VerCambiosYValidacionesResultados verCambiosResultados,
            VerHistorialCompletoPaciente verHistorialPaciente,
            AuditLogMapper mapper
    ) {
        this.consultarAccionesCriticas = consultarAccionesCriticas;
        this.consultarCambiosPorUsuario = consultarCambiosPorUsuario;
        this.consultarPorEntidad = consultarPorEntidad;
        this.verCambiosResultados = verCambiosResultados;
        this.verHistorialPaciente = verHistorialPaciente;
        this.mapper = mapper;
    }

    // ======================
    // ACCIONES CRÍTICAS
    // ======================
    @GetMapping("/acciones-criticas")
    public List<AuditLogDtoResponse> accionesCriticas() {
        return consultarAccionesCriticas.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // CAMBIOS POR USUARIO
    // ======================
    @GetMapping("/usuarios/{userId}")
    public List<AuditLogDtoResponse> cambiosPorUsuario(
            @PathVariable Long userId
    ) {
        return consultarCambiosPorUsuario.ejecutar(userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // HISTORIAL POR ENTIDAD
    // ======================
    @GetMapping("/entidades")
    public List<AuditLogDtoResponse> historialPorEntidad(
            @RequestParam String entityName,
            @RequestParam Long entityId
    ) {
        return consultarPorEntidad.ejecutar(entityName, entityId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // RESULTADOS
    // ======================
    @GetMapping("/resultados/{resultadoId}")
    public List<AuditLogDtoResponse> historialResultados(
            @PathVariable Long resultadoId
    ) {
        return verCambiosResultados.ejecutar(resultadoId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // PACIENTES
    // ======================
    @GetMapping("/pacientes/{pacienteId}")
    public List<AuditLogDtoResponse> historialPaciente(
            @PathVariable Long pacienteId
    ) {
        return verHistorialPaciente.ejecutar(pacienteId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

