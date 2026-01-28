package com.laboratorio.analisis_clinico.areaMedica.adapter.in.web;

import com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.dto.AreaMedicaDtoRequest;
import com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.dto.AreaMedicaDtoResponse;
import com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.mapper.AreaMedicaWebMapper;
import com.laboratorio.analisis_clinico.areaMedica.application.usecase.Queries.ConsultarAreaMedica;
import com.laboratorio.analisis_clinico.areaMedica.application.usecase.Queries.ListarAreasMedicas;
import com.laboratorio.analisis_clinico.areaMedica.application.usecase.commands.ActivarDesactivarAreaMedica;
import com.laboratorio.analisis_clinico.areaMedica.application.usecase.commands.CrearAreaMedica;
import com.laboratorio.analisis_clinico.areaMedica.application.usecase.commands.EditarAreaMedica;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas-medicas")
public class AreaMedicaController {

    private final CrearAreaMedica crearAreaMedica;
    private final EditarAreaMedica editarAreaMedica;
    private final ActivarDesactivarAreaMedica activarDesactivarAreaMedica;
    private final ConsultarAreaMedica consultarAreaMedica;
    private final ListarAreasMedicas listarAreasMedicas;
    private final AreaMedicaWebMapper mapper;

    public AreaMedicaController(
            CrearAreaMedica crearAreaMedica,
            EditarAreaMedica editarAreaMedica,
            ActivarDesactivarAreaMedica activarDesactivarAreaMedica,
            ConsultarAreaMedica consultarAreaMedica,
            ListarAreasMedicas listarAreasMedicas,
            AreaMedicaWebMapper mapper
    ) {
        this.crearAreaMedica = crearAreaMedica;
        this.editarAreaMedica = editarAreaMedica;
        this.activarDesactivarAreaMedica = activarDesactivarAreaMedica;
        this.consultarAreaMedica = consultarAreaMedica;
        this.listarAreasMedicas = listarAreasMedicas;
        this.mapper = mapper;
    }

    // ======================
    // CREAR
    // ======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody AreaMedicaDtoRequest request) {
        crearAreaMedica.ejecutar(
                request.getNombreArea(),
                request.getDescripcion()
        );
    }

    // ======================
    // EDITAR
    // ======================
    @PutMapping("/{id}")
    public void editar(
            @PathVariable Long id,
            @Valid @RequestBody AreaMedicaDtoRequest request
    ) {
        editarAreaMedica.ejecutar(
                id,
                request.getNombreArea(),
                request.getDescripcion()
        );
    }

    // ======================
    // ACTIVAR / DESACTIVAR
    // ======================
    @PatchMapping("/{id}/activar")
    public void activar(@PathVariable Long id) {
        activarDesactivarAreaMedica.activar(id);
    }

    @PatchMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) {
        activarDesactivarAreaMedica.desactivar(id);
    }

    // ======================
    // CONSULTAR
    // ======================
    @GetMapping("/{id}")
    public AreaMedicaDtoResponse consultar(@PathVariable Long id) {
        return mapper.toResponse(
                consultarAreaMedica.ejecutar(id)
        );
    }

    // ======================
    // LISTAR
    // ======================
    @GetMapping
    public List<AreaMedicaDtoResponse> listar() {
        return listarAreasMedicas.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

