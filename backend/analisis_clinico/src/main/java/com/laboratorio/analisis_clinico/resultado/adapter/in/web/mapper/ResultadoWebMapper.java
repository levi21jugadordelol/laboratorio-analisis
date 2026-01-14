package com.laboratorio.analisis_clinico.resultado.adapter.in.web.mapper;
import com.laboratorio.analisis_clinico.resultado.adapter.in.web.dto.ResultadoDtoRequest;
import com.laboratorio.analisis_clinico.resultado.adapter.in.web.dto.ResultadoDtoResponse;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ResultadoWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idResultado", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "validado", ignore = true)
    Resultado toDomain(ResultadoDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    ResultadoDtoResponse toResponse(Resultado resultado);
}

