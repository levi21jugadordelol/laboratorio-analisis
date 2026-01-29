package com.laboratorio.analisis_clinico.resultado.adapter.in.web;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.resultado.adapter.in.web.dto.ResultadoDtoRequest;
import com.laboratorio.analisis_clinico.resultado.adapter.in.web.dto.ResultadoDtoResponse;
import com.laboratorio.analisis_clinico.resultado.adapter.in.web.mapper.ResultadoWebMapper;
import com.laboratorio.analisis_clinico.resultado.application.usecase.Queries.ConsultarResultado;
import com.laboratorio.analisis_clinico.resultado.application.usecase.Queries.ListarResultadosPorFecha;
import com.laboratorio.analisis_clinico.resultado.application.usecase.Queries.VerHistorialVersionesResultado;
import com.laboratorio.analisis_clinico.resultado.application.usecase.commands.ConsultarResultadoPorOrdenAnalisis;
import com.laboratorio.analisis_clinico.resultado.application.usecase.commands.CrearResultado;
import com.laboratorio.analisis_clinico.resultado.application.usecase.commands.ValidarResultado;
import com.laboratorio.analisis_clinico.resultado.application.usecase.commands.VersionarResultadosModificados;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    // ======================
    // COMMANDS
    // ======================
    private final CrearResultado crearResultado;
    private final ValidarResultado validarResultado;
    private final VersionarResultadosModificados versionarResultados;

    // ======================
    // QUERIES
    // ======================
    private final ConsultarResultado consultarResultado;
    private final ConsultarResultadoPorOrdenAnalisis consultarPorOrdenAnalisis;
    private final ListarResultadosPorFecha listarResultadosPorFecha;
    private final VerHistorialVersionesResultado verHistorialVersiones;

    // ======================
    // MAPPER
    // ======================
    private final ResultadoWebMapper mapper;

    // =====================================================
    // CREAR RESULTADO INICIAL
    // =====================================================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody ResultadoDtoRequest request) {

        crearResultado.ejecutar(
                request.getOrdenAnalisisId(),
                request.getResultadoJson(),
                request.getObservacion(),
                request.getCreatedByUsuario()
        );
    }

    // =====================================================
    // CONSULTAR RESULTADO POR ID
    // =====================================================
    @GetMapping("/{id}")
    public ResultadoDtoResponse consultar(@PathVariable Long id) {

        Resultado resultado = consultarResultado.ejecutar(id);
        return mapper.toResponse(resultado);
    }

    // =====================================================
    // CONSULTAR RESULTADO POR ORDEN-ANÁLISIS
    // =====================================================
    @GetMapping("/orden-analisis/{ordenAnalisisId}")
    public ResultadoDtoResponse consultarPorOrdenAnalisis(
            @PathVariable Long ordenAnalisisId
    ) {
        Resultado resultado =
                consultarPorOrdenAnalisis.ejecutar(ordenAnalisisId);

        return mapper.toResponse(resultado);
    }

    // =====================================================
    // VALIDAR RESULTADO
    // =====================================================
    @PostMapping("/{id}/validar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validar(@PathVariable Long id) {

        validarResultado.ejecutar(id);
    }

    // =====================================================
    // CREAR NUEVA VERSIÓN DEL RESULTADO
    // =====================================================
    @PostMapping("/{id}/versionar")
    @ResponseStatus(HttpStatus.CREATED)
    public void versionar(
            @PathVariable Long id,
            @RequestBody Map<String, Object> nuevoResultado,
            @RequestParam String motivo,
            @RequestParam Long usuarioId
    ) {
        versionarResultados.ejecutar(
                id,
                nuevoResultado,
                motivo,
                usuarioId
        );
    }

    // =====================================================
    // LISTAR RESULTADOS POR FECHA
    // =====================================================
    @GetMapping("/fecha")
    public List<ResultadoDtoResponse> listarPorFecha(
            @RequestParam LocalDateTime desde,
            @RequestParam LocalDateTime hasta
    ) {
        return listarResultadosPorFecha.ejecutar(desde, hasta)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // =====================================================
    // VER HISTORIAL DE VERSIONES (AUDITORÍA)
    // =====================================================
    @GetMapping("/{id}/historial")
    public List<AuditLog> verHistorial(@PathVariable Long id) {

        return verHistorialVersiones.ejecutar(id);
    }
}

