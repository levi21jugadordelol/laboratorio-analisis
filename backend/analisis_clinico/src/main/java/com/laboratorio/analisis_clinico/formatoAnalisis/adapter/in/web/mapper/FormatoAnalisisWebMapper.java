package com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.dto.FormatoAnalisisDtoRequest;
import com.laboratorio.analisis_clinico.formatoAnalisis.adapter.in.web.dto.FormatoAnalisisDtoResponse;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface FormatoAnalisisWebMapper {
    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idFormatoAnalisis", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "estadoFormato", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    FormatoAnalisis toDomain(FormatoAnalisisDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    FormatoAnalisisDtoResponse toResponse(FormatoAnalisis formato);
}
