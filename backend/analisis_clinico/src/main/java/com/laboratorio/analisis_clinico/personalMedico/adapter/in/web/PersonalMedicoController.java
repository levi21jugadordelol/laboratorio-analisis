package com.laboratorio.analisis_clinico.personalMedico.adapter.in.web;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.dto.PersonalMedicoDtoRequest;
import com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.dto.PersonalMedicoDtoResponse;
import com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.mapper.PersonalMedicoWebMapper;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.Queries.ConsultarOrdenesPorPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.Queries.ConsultarPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.Queries.VerCambiosDatosPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands.ActivarPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands.DesactivarPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands.EditarPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.application.usecase.commands.RegistrarPersonalMedico;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-medico")
@RequiredArgsConstructor
public class PersonalMedicoController {

    private final RegistrarPersonalMedico registrarPersonalMedico;
    private final EditarPersonalMedico editarPersonalMedico;
    private final ActivarPersonalMedico activarPersonalMedico;
    private final DesactivarPersonalMedico desactivarPersonalMedico;
    private final ConsultarPersonalMedico consultarPersonalMedico;
    private final ConsultarOrdenesPorPersonalMedico consultarOrdenesPorPersonalMedico;
    private final VerCambiosDatosPersonalMedico verCambiosDatosPersonalMedico;

    private final PersonalMedicoWebMapper mapper;

    // =========================
    // REGISTRAR PERSONAL MÉDICO
    // =========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registrar(
            @Valid @RequestBody PersonalMedicoDtoRequest request
    ) {
        registrarPersonalMedico.ejecutar(
                request.getNombrePersonalMedico(),
                request.getApellidoPersonalMedico(),
                request.getEspecialidad()
        );
    }

    // =========================
    // EDITAR PERSONAL MÉDICO
    // =========================
    @PutMapping("/{id}")
    public void editar(
            @PathVariable Long id,
            @Valid @RequestBody PersonalMedicoDtoRequest request
    ) {
        editarPersonalMedico.ejecutar(
                id,
                request.getNombrePersonalMedico(),
                request.getApellidoPersonalMedico(),
                request.getEspecialidad()
        );
    }

    // =========================
    // ACTIVAR
    // =========================
    @PatchMapping("/{id}/activar")
    public void activar(@PathVariable Long id) {
        activarPersonalMedico.ejecutar(id);
    }

    // =========================
    // DESACTIVAR
    // =========================
    @PatchMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) {
        desactivarPersonalMedico.ejecutar(id);
    }

    // =========================
    // CONSULTAR POR ID
    // =========================
    @GetMapping("/{id}")
    public PersonalMedicoDtoResponse consultar(@PathVariable Long id) {
        PersonalMedico medico = consultarPersonalMedico.ejecutar(id);
        return mapper.toResponse(medico);
    }

    // =========================
    // ÓRDENES ASIGNADAS
    // =========================
    @GetMapping("/{id}/ordenes")
    public List<Orden> consultarOrdenes(@PathVariable Long id) {
        return consultarOrdenesPorPersonalMedico.ejecutar(id);
    }

    // =========================
    // HISTORIAL DE CAMBIOS
    // =========================
    @GetMapping("/{id}/historial")
    public List<AuditLog> verHistorial(@PathVariable Long id) {
        return verCambiosDatosPersonalMedico.ejecutar(id);
    }
}

