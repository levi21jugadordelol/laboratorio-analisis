package com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.dto.AreaMedicaDtoRequest;
import com.laboratorio.analisis_clinico.areaMedica.adapter.in.web.dto.AreaMedicaDtoResponse;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AreaMedicaWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idArea", ignore = true)
    @Mapping(target = "estadoAreaMedica", ignore = true)
    AreaMedica toDomain(AreaMedicaDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    AreaMedicaDtoResponse toResponse(AreaMedica areaMedica);
}
