package com.laboratorio.analisis_clinico.usuario.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.usuario.adapter.in.web.dto.UsuarioDtoRequest;
import com.laboratorio.analisis_clinico.usuario.adapter.in.web.dto.UsuarioDtoResponse;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UsuarioWebMapper {

    // ======================
    // Request → Dominio
    // ======================

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "estadoUsuario", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Usuario toDomain(UsuarioDtoRequest request);

    // ======================
    // Dominio → Response
    // ======================

    UsuarioDtoResponse toResponse(Usuario usuario);
}

