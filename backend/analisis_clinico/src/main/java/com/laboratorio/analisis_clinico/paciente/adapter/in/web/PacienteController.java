package com.laboratorio.analisis_clinico.paciente.adapter.in.web;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.paciente.adapter.in.web.dto.PacienteDtoRequest;
import com.laboratorio.analisis_clinico.paciente.adapter.in.web.dto.PacienteDtoResponse;
import com.laboratorio.analisis_clinico.paciente.adapter.in.web.mapper.PacienteWebMapper;
import com.laboratorio.analisis_clinico.paciente.application.exception.PacienteNotFoundException;
import com.laboratorio.analisis_clinico.paciente.application.usecase.Queries.BuscarPacientePorDniONombre;
import com.laboratorio.analisis_clinico.paciente.application.usecase.Queries.ConsultarPaciente;
import com.laboratorio.analisis_clinico.paciente.application.usecase.Queries.VerHistorialCambiosPaciente;
import com.laboratorio.analisis_clinico.paciente.application.usecase.commands.CrearPaciente;
import com.laboratorio.analisis_clinico.paciente.application.usecase.commands.EditarPaciente;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final CrearPaciente crearPaciente;
    private final EditarPaciente editarPaciente;
    private final BuscarPacientePorDniONombre buscarPaciente;
    private final ConsultarPaciente consultarPaciente;
    private final VerHistorialCambiosPaciente verHistorialCambiosPaciente;

    private final PacienteWebMapper mapper;

    // =========================
    // CREAR PACIENTE
    // =========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody PacienteDtoRequest request) {

        crearPaciente.ejecutar(
                request.getNombrePaciente(),
                request.getApellidoPaternoPaciente(),
                request.getApellidoMaternoPaciente(),
                request.getDni(),
                request.getSexo(),
                request.getEdad(),
                request.getTipoPaciente(),
                request.getNumeroHistorialClinica(),
                request.getZonaProcedencia(),
                request.getTelefono()
        );
    }

    // =========================
    // EDITAR PACIENTE
    // =========================
    @PutMapping("/{id}")
    public void editar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteDtoRequest request
    ) {

        editarPaciente.ejecutar(
                id,
                request.getNombrePaciente(),
                request.getApellidoPaternoPaciente(),
                request.getApellidoMaternoPaciente(),
                request.getEdad(),
                request.getZonaProcedencia(),
                request.getTelefono()
        );
    }

    // =========================
    // CONSULTAR POR ID
    // =========================
    @GetMapping("/{id}")
    public PacienteDtoResponse consultar(@PathVariable Long id) {

        Paciente paciente = consultarPaciente.ejecutar(id);
        return mapper.toResponse(paciente);
    }

    // =========================
    // BUSCAR POR DNI
    // =========================
    @GetMapping("/buscar")
    public PacienteDtoResponse buscarPorDni(
            @RequestParam String dni
    ) {
        Paciente paciente = buscarPaciente.buscarPorDni(dni)
                .orElseThrow(() ->
                        new PacienteNotFoundException("Paciente no encontrado.")
                );

        return mapper.toResponse(paciente);
    }

    // =========================
    // HISTORIAL DE CAMBIOS
    // =========================
    @GetMapping("/{id}/historial")
    public List<AuditLog> verHistorial(@PathVariable Long id) {
        return verHistorialCambiosPaciente.ejecutar(id);
    }
}

