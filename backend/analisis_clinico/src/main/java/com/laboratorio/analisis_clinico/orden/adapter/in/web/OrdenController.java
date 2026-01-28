package com.laboratorio.analisis_clinico.orden.adapter.in.web;

import com.laboratorio.analisis_clinico.orden.adapter.in.web.dto.OrdenDtoRequest;
import com.laboratorio.analisis_clinico.orden.adapter.in.web.dto.OrdenDtoResponse;
import com.laboratorio.analisis_clinico.orden.adapter.in.web.mapper.OrdenWebMapper;
import com.laboratorio.analisis_clinico.orden.application.usecase.Queries.ConsultarOrden;
import com.laboratorio.analisis_clinico.orden.application.usecase.Queries.ConsultarOrdenesPorFecha;
import com.laboratorio.analisis_clinico.orden.application.usecase.Queries.ConsultarOrdenesPorPaciente;

import com.laboratorio.analisis_clinico.orden.application.usecase.commands.CancelarOrden;
import com.laboratorio.analisis_clinico.orden.application.usecase.commands.CrearOrden;
import com.laboratorio.analisis_clinico.orden.application.usecase.commands.EditarOrden;
import com.laboratorio.analisis_clinico.orden.application.usecase.commands.RecepcionarOrden;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    // ======================
    // CASOS DE USO
    // ======================

    private final CrearOrden crearOrden;
    private final CancelarOrden cancelarOrden;
    private final EditarOrden editarOrden;
    private final RecepcionarOrden recepcionarOrden;

    private final ConsultarOrden consultarOrden;
    private final ConsultarOrdenesPorPaciente consultarOrdenesPorPaciente;
    private final ConsultarOrdenesPorFecha consultarOrdenesPorFecha;

    private final OrdenWebMapper mapper;

    // ======================
    // CREAR ORDEN
    // ======================

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody OrdenDtoRequest request) {

        crearOrden.ejecutar(
                request.getPacienteId(),
                request.getTipoOrden(),
                request.getDoctorId(),
                request.getCreatedByUsuario()
        );
    }

    // ======================
    // CONSULTAR ORDEN POR ID
    // ======================

    @GetMapping("/{id}")
    public OrdenDtoResponse consultar(@PathVariable Long id) {

        return mapper.toResponse(
                consultarOrden.ejecutar(id)
        );
    }

    // ======================
    // RECEPCIONAR ORDEN
    // ======================

    @PostMapping("/{id}/recepcionar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void recepcionar(@PathVariable Long id) {

        recepcionarOrden.ejecutar(id);
    }

    // ======================
    // CANCELAR ORDEN
    // ======================

    @PostMapping("/{id}/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {

        cancelarOrden.ejecutar(id);
    }

    // ======================
    // CAMBIAR MÉDICO
    // ======================

    @PutMapping("/{id}/medico/{medicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cambiarMedico(
            @PathVariable Long id,
            @PathVariable Long medicoId
    ) {
        editarOrden.ejecutar(id, medicoId);
    }

    // ======================
    // CONSULTAR ÓRDENES POR PACIENTE
    // ======================

    @GetMapping("/paciente/{pacienteId}")
    public List<OrdenDtoResponse> consultarPorPaciente(
            @PathVariable Long pacienteId
    ) {

        return consultarOrdenesPorPaciente.ejecutar(pacienteId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // CONSULTAR ÓRDENES POR FECHA
    // ======================

    @GetMapping("/fecha")
    public List<OrdenDtoResponse> consultarPorFecha(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin
    ) {

        return consultarOrdenesPorFecha.ejecutar(inicio, fin)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

