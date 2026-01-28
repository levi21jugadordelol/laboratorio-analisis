package com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web;

import com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.dto.FormatoAnalisisDtoRequest;
import com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.dto.FormatoAnalisisDtoResponse;
import com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.mapper.FormatoAnalisisWebMapper;
import com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.Queries.*;
import com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands.CrearFormatoAnalisis;
import com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands.CrearNuevaVersionFormatoAnalisis;
import com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands.EditarFormatoAnalisis;
import com.laboratorio.analisis_clinico.formatoAnalisis.application.usecase.commands.MarcarFormatoAnalisisComoObsoleto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/formatos-analisis")
@RequiredArgsConstructor
public class FormatoAnalisisController {

    private final CrearFormatoAnalisis crearFormato;
    private final CrearNuevaVersionFormatoAnalisis crearNuevaVersion;
    private final EditarFormatoAnalisis editarFormato;
    private final MarcarFormatoAnalisisComoObsoleto marcarObsoleto;

    private final ConsultarFormatoAnalisisPorIdONombre consultarPorIdONombre;
    private final ConsultarFormatosPorAnalisis consultarPorAnalisis;
    private final ConsultarFormatosPorVersion consultarPorVersion;
    private final VerHistorialVersionesFormato verHistorialVersiones;
    private final ListarFormatosDisponibles listarDisponibles;
    private final ListarFormatosNoDisponibles listarNoDisponibles;

    private final FormatoAnalisisWebMapper mapper;

    // ======================
    // CREAR FORMATO
    // ======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody FormatoAnalisisDtoRequest request) {

        crearFormato.ejecutar(
                request.getAnalisisId(),
                request.getNombreFormato(),
                request.getDescripcion(),
                request.getEstructuraFormato(),
                request.getCreatedByUsuario()
        );
    }

    // ======================
    // NUEVA VERSION
    // ======================
    @PostMapping("/{id}/version")
    @ResponseStatus(HttpStatus.CREATED)
    public void crearNuevaVersion(
            @PathVariable Long id,
            @RequestBody Map<String, Object> nuevaEstructura,
            @RequestParam Long usuarioId
    ) {
        crearNuevaVersion.ejecutar(id, nuevaEstructura, usuarioId);
    }

    // ======================
    // EDITAR
    // ======================
    @PutMapping("/{id}")
    public void editar(
            @PathVariable Long id,
            @Valid @RequestBody FormatoAnalisisDtoRequest request
    ) {
        editarFormato.ejecutar(
                id,
                request.getNombreFormato(),
                request.getDescripcion()
        );
    }

    // ======================
    // MARCAR OBSOLETO
    // ======================
    @PutMapping("/{id}/obsoleto")
    public void marcarObsoleto(@PathVariable Long id) {
        marcarObsoleto.ejecutar(id);
    }

    // ======================
    // CONSULTAR POR ID
    // ======================
    @GetMapping("/{id}")
    public FormatoAnalisisDtoResponse consultarPorId(@PathVariable Long id) {
        return mapper.toResponse(
                consultarPorIdONombre.ejecutarPorId(id)
        );
    }

    // ======================
    // CONSULTAR POR NOMBRE
    // ======================
    @GetMapping("/analisis/{analisisId}/nombre/{nombre}")
    public FormatoAnalisisDtoResponse consultarPorNombre(
            @PathVariable Long analisisId,
            @PathVariable String nombre
    ) {
        return mapper.toResponse(
                consultarPorIdONombre.ejecutarPorNombre(analisisId, nombre)
        );
    }

    // ======================
    // LISTAR POR ANALISIS
    // ======================
    @GetMapping("/analisis/{analisisId}")
    public List<FormatoAnalisisDtoResponse> listarPorAnalisis(
            @PathVariable Long analisisId
    ) {
        return consultarPorAnalisis.ejecutar(analisisId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // LISTAR VIGENTES
    // ======================
    @GetMapping("/analisis/{analisisId}/vigentes")
    public List<FormatoAnalisisDtoResponse> listarVigentes(
            @PathVariable Long analisisId
    ) {
        return listarDisponibles.ejecutar(analisisId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // LISTAR OBSOLETOS
    // ======================
    @GetMapping("/analisis/{analisisId}/obsoletos")
    public List<FormatoAnalisisDtoResponse> listarObsoletos(
            @PathVariable Long analisisId
    ) {
        return listarNoDisponibles.ejecutar(analisisId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // HISTORIAL VERSIONES
    // ======================
    @GetMapping("/analisis/{analisisId}/historial/{nombre}")
    public List<FormatoAnalisisDtoResponse> historialVersiones(
            @PathVariable Long analisisId,
            @PathVariable String nombre
    ) {
        return verHistorialVersiones.ejecutar(analisisId, nombre)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

