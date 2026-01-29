package com.laboratorio.analisis_clinico.reporte.adapter.in.web;

import com.laboratorio.analisis_clinico.reporte.adapter.in.web.dto.ReporteDtoRequest;
import com.laboratorio.analisis_clinico.reporte.adapter.in.web.dto.ReporteDtoResponse;
import com.laboratorio.analisis_clinico.reporte.adapter.in.web.mapper.ReporteWebMapper;
import com.laboratorio.analisis_clinico.reporte.application.usecase.Queries.ConsultarReporte;
import com.laboratorio.analisis_clinico.reporte.application.usecase.Queries.VerHistorialReportesGenerados;
import com.laboratorio.analisis_clinico.reporte.application.usecase.commands.GenerarReporteAdHoc;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    // ======================
    // COMMANDS
    // ======================
    private final GenerarReporteAdHoc generarReporteAdHoc;

    // ======================
    // QUERIES
    // ======================
    private final ConsultarReporte consultarReporte;
    private final VerHistorialReportesGenerados verHistorialReportesGenerados;

    // ======================
    // MAPPER
    // ======================
    private final ReporteWebMapper mapper;

    // =====================================================
    // CREAR REPORTE (AD HOC)
    // =====================================================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void generarReporte(@Valid @RequestBody ReporteDtoRequest request) {

        generarReporteAdHoc.ejecutar(
                request.getTipoReporte(),
                request.getPeriodo(),
                request.getArchivoUrl(),
                request.isEnvioAutomaticoCorreo(),
                request.getCreatedByUsuario()
        );
    }

    // =====================================================
    // CONSULTAR REPORTE POR ID
    // =====================================================
    @GetMapping("/{id}")
    public ReporteDtoResponse consultar(@PathVariable Long id) {

        Reporte reporte = consultarReporte.ejecutar(id);
        return mapper.toResponse(reporte);
    }

    // =====================================================
    // LISTAR HISTORIAL DE REPORTES
    // =====================================================
    @GetMapping
    public List<ReporteDtoResponse> listarHistorial() {

        return verHistorialReportesGenerados.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

