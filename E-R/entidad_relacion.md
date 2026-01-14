### areaMedica **(EC)**

- idArea
- nombreArea
- descripcion
- estado (enum: activa, inactiva)

### personalMedico **(EC)**

- idPersonalMedico
- nombrePersonalMedico
- apellidoPersonalMedico
- especialidad
- estado (enum: activo, inactivo)

### usuario **(EC)**

- idUsuario
- nombreUsuario
- email
- rol (enum: laboratorista, admin)
- estado (enum:activo,inactivo)
- fechaCreacion

### analisis **(EC)**

- idAnalisis
- nombreAnalisis
- descripcion
- areaMedica_id
- estado (enum: activo, inactivo)

### formatoAnalisis **(estructura versionada del análisis)**

- idFormatoAnalisis
- analisis_id
- nombreFormato
- descripcion
- estructura_json
- version
- createdBy (_usuario_)
- fecha_creacion
- estado (enum: vigente, obsoleto)

### paciente **(EC)**

- idPaciente
- nombrePaciente
- apellidoPaternoPaciente
- apellidoMaternoPaciente
- dni (_unique_)
- sexo (_M-F_)
- edad
- zona (_lugar de procedencia_)
- tipoPaciente (_enum: asegurado, particular, essalud_)
- numeroHistoriaClinica
- fechaRegistro
- telefono

### orden **(EC)**

- idOrden
- doctorId (_nullable, si es particular_)
- paciente_id
- tipoOrden (_enum: receta, particular_)
- estado (_enum: creada, en_proceso, finalizada, anulada_)
- fecha_creacion
- createdBy (_usuario_)

### ordenMedicaAndAnalisis **(ER)**

(_tabla intermedia - detalle de análisis solicitados en una orden_)

- idOrdenAnalisis
- ordenMedica_id
- formatoAnalisis_id
- fecha_realizacion
- estado (_enum: pendiente,proceso,completado,cancelada_)
- prioridad (_enum:normal,urgente_)

### resultado **(EC)**

(_resultado actual del análisis_)

- idResultado
- ordenMedicaAndAnalisis_id
- resultado_json
- fechaRegistro
- observacion
- createdBy (_usuario_)
- version

### reporte **(EC)**

- idReporte
- tipo (_enum: quincena,mensual,paciente,comparativo_)
- periodo
- fechaGenerada
- archivoUrl
- envioAutomaticoCorreo
- createdBy (_usuario_)

### auditlog

- id_audit
- entity_name
- entity_id
- action (_enum:create,update,delete,validate_)
- user_id
- timestamp
- diff_json **que cambio**
- reason **motivo, si aplica**

## relaciones

- una **areaMedica** puede mandar muchos **analisis** (_1-M_)

- un **analisis** puede tener varios **formatoAnalisis** (_1-M_)

- un **paciente** puede tener muchas **ordenes** (_1-M_)

- un **personalMedico** puede hacer muchas **ordenes** (_1-M_)

- una **orden** puede tener varias **ordenMedicaAndAnalisis** (_1-M_)

- un **formatoAnalisis** puede tener varias **ordenMedicaAndAnalisis** (_1-M_)

- un **ordenMedicaAndAnalisis** solo puede tener un **resultado** (_1-1_)

- un **usuario** puede hacer varias **(orden, formatoAnalisis, resultado, reporte, auditlog)** (_1-M_)
