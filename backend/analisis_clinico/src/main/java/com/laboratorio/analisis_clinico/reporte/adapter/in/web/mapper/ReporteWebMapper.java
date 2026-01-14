package com.laboratorio.analisis_clinico.reporte.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.reporte.adapter.in.web.dto.ReporteDtoRequest;
import com.laboratorio.analisis_clinico.reporte.adapter.in.web.dto.ReporteDtoResponse;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ReporteWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idReporte", ignore = true)
    @Mapping(target = "fechaGenerada", ignore = true)
    Reporte toDomain(ReporteDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    ReporteDtoResponse toResponse(Reporte reporte);
}

