package com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.dto.OrdenAnalisisDtoRequest;
import com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.dto.OrdenAnalisisDtoResponse;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrdenAnalisisWebMapper {
    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idOrdenAnalisis", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRealizacion", ignore = true)
    OrdenAnalisis toDomain(OrdenAnalisisDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    OrdenAnalisisDtoResponse toResponse(OrdenAnalisis ordenAnalisis);
}
