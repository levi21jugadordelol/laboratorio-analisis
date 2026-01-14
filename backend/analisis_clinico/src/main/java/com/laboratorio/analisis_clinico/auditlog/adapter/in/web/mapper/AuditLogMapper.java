package com.laboratorio.analisis_clinico.auditlog.adapter.in.web.mapper;

import com.laboratorio.analisis_clinico.auditlog.adapter.in.web.dto.AuditLogDtoResponse;
import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuditLogMapper {

    AuditLogDtoResponse toResponse(AuditLog auditLog);
}

