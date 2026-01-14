package com.laboratorio.analisis_clinico.paciente.adapter.in.web.mapper;
import com.laboratorio.analisis_clinico.paciente.adapter.in.web.dto.PacienteDtoRequest;
import com.laboratorio.analisis_clinico.paciente.adapter.in.web.dto.PacienteDtoResponse;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PacienteWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idPaciente", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Paciente toDomain(PacienteDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    PacienteDtoResponse toResponse(Paciente paciente);
}

