package com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.dto.PersonalMedicoDtoRequest;
import com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.dto.PersonalMedicoDtoResponse;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PersonalMedicoWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idPersonalMedico", ignore = true)
    @Mapping(target = "estadoPersonalMedico", ignore = true)
    PersonalMedico toDomain(PersonalMedicoDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    PersonalMedicoDtoResponse toResponse(PersonalMedico personalMedico);
}

