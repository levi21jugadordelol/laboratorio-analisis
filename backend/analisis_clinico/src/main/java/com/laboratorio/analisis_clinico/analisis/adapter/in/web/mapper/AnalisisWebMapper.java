package com.laboratorio.analisis_clinico.analisis.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.analisis.adapter.in.web.dto.AnalisisDtoRequest;
import com.laboratorio.analisis_clinico.analisis.adapter.in.web.dto.AnalisisDtoResponse;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AnalisisWebMapper {
    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idAnalisis", ignore = true)
    @Mapping(target = "estadoAnalisis", ignore = true)
    Analisis toDomain(AnalisisDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    AnalisisDtoResponse toResponse(Analisis analisis);
}
