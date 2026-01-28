package com.laboratorio.analisis_clinico.analisis.adapter.in.web;

import com.laboratorio.analisis_clinico.analisis.adapter.in.web.dto.AnalisisDtoRequest;
import com.laboratorio.analisis_clinico.analisis.adapter.in.web.dto.AnalisisDtoResponse;
import com.laboratorio.analisis_clinico.analisis.adapter.in.web.mapper.AnalisisWebMapper;
import com.laboratorio.analisis_clinico.analisis.application.usecase.Queries.ConsultarAnalisis;
import com.laboratorio.analisis_clinico.analisis.application.usecase.Queries.ListarAnalisisDisponibles;
import com.laboratorio.analisis_clinico.analisis.application.usecase.Queries.ListarAnalisisNoDisponibles;
import com.laboratorio.analisis_clinico.analisis.application.usecase.commands.ActivarDesactivarAnalisis;
import com.laboratorio.analisis_clinico.analisis.application.usecase.commands.CrearAnalisisClinico;
import com.laboratorio.analisis_clinico.analisis.application.usecase.commands.EditarAnalisis;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analisis")
public class AnalisisController {

    private final CrearAnalisisClinico crearAnalisis;
    private final EditarAnalisis editarAnalisis;
    private final ActivarDesactivarAnalisis activarDesactivarAnalisis;
    private final ConsultarAnalisis consultarAnalisis;
    private final ListarAnalisisDisponibles listarDisponibles;
    private final ListarAnalisisNoDisponibles listarNoDisponibles;
    private final AnalisisWebMapper mapper;

    public AnalisisController(
            CrearAnalisisClinico crearAnalisis,
            EditarAnalisis editarAnalisis,
            ActivarDesactivarAnalisis activarDesactivarAnalisis,
            ConsultarAnalisis consultarAnalisis,
            ListarAnalisisDisponibles listarDisponibles,
            ListarAnalisisNoDisponibles listarNoDisponibles,
            AnalisisWebMapper mapper
    ) {
        this.crearAnalisis = crearAnalisis;
        this.editarAnalisis = editarAnalisis;
        this.activarDesactivarAnalisis = activarDesactivarAnalisis;
        this.consultarAnalisis = consultarAnalisis;
        this.listarDisponibles = listarDisponibles;
        this.listarNoDisponibles = listarNoDisponibles;
        this.mapper = mapper;
    }

    // ======================
    // CREAR
    // ======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody AnalisisDtoRequest request) {
        crearAnalisis.ejecutar(
                request.getNombreAnalisis(),
                request.getDescripcion(),
                request.getAreaMedicaId()
        );
    }

    // ======================
    // EDITAR
    // ======================
    @PutMapping("/{id}")
    public void editar(
            @PathVariable Long id,
            @Valid @RequestBody AnalisisDtoRequest request
    ) {
        editarAnalisis.ejecutar(
                id,
                request.getNombreAnalisis(),
                request.getDescripcion()
        );
    }

    // ======================
    // ACTIVAR / DESACTIVAR
    // ======================
    @PatchMapping("/{id}/activar")
    public void activar(@PathVariable Long id) {
        activarDesactivarAnalisis.activar(id);
    }

    @PatchMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) {
        activarDesactivarAnalisis.desactivar(id);
    }

    // ======================
    // CONSULTAR
    // ======================
    @GetMapping("/{id}")
    public AnalisisDtoResponse consultar(@PathVariable Long id) {
        return mapper.toResponse(
                consultarAnalisis.ejecutar(id)
        );
    }

    // ======================
    // LISTAR
    // ======================
    @GetMapping("/disponibles")
    public List<AnalisisDtoResponse> listarDisponibles() {
        return listarDisponibles.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/no-disponibles")
    public List<AnalisisDtoResponse> listarNoDisponibles() {
        return listarNoDisponibles.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

