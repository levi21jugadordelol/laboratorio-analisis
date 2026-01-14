package com.laboratorio.analisis_clinico.orden.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.orden.adapter.in.web.dto.OrdenDtoRequest;
import com.laboratorio.analisis_clinico.orden.adapter.in.web.dto.OrdenDtoResponse;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrdenWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idOrden", ignore = true)
    @Mapping(target = "estadoOrden", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Orden toDomain(OrdenDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    OrdenDtoResponse toResponse(Orden orden);
}

