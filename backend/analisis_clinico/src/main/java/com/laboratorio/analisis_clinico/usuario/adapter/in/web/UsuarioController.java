package com.laboratorio.analisis_clinico.usuario.adapter.in.web;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.usuario.adapter.in.web.dto.UsuarioDtoRequest;
import com.laboratorio.analisis_clinico.usuario.adapter.in.web.dto.UsuarioDtoResponse;
import com.laboratorio.analisis_clinico.usuario.adapter.in.web.mapper.UsuarioWebMapper;
import com.laboratorio.analisis_clinico.usuario.application.usecase.Queries.ConsultarUsuarios;
import com.laboratorio.analisis_clinico.usuario.application.usecase.Queries.ValidarRolUsuario;
import com.laboratorio.analisis_clinico.usuario.application.usecase.Queries.VerAccionesUsuario;
import com.laboratorio.analisis_clinico.usuario.application.usecase.commands.ActivarDesactivarUsuario;
import com.laboratorio.analisis_clinico.usuario.application.usecase.commands.CrearUsuario;
import com.laboratorio.analisis_clinico.usuario.application.usecase.commands.EditarUsuario;
import com.laboratorio.analisis_clinico.usuario.application.usecase.commands.IniciarSesion;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CrearUsuario crearUsuario;
    private final EditarUsuario editarUsuario;
    private final ActivarDesactivarUsuario activarDesactivarUsuario;
    private final ConsultarUsuarios consultarUsuarios;
    private final IniciarSesion iniciarSesion;
    private final ValidarRolUsuario validarRolUsuario;
    private final VerAccionesUsuario verAccionesUsuario;

    private final UsuarioWebMapper mapper;

    // ======================
    // CREAR USUARIO
    // ======================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crear(@Valid @RequestBody UsuarioDtoRequest request) {
        crearUsuario.ejecutar(
                request.getNombreUsuario(),
                request.getEmail(),
                request.getRolUsuario()
        );
    }

    // ======================
    // EDITAR USUARIO
    // ======================
    @PutMapping("/{id}")
    public void editar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDtoRequest request
    ) {
        editarUsuario.ejecutar(
                id,
                request.getNombreUsuario(),
                request.getEmail()
        );
    }

    // ======================
    // ACTIVAR / DESACTIVAR
    // ======================
    @PostMapping("/{id}/activar")
    public void activar(@PathVariable Long id) {
        activarDesactivarUsuario.activar(id);
    }

    @PostMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) {
        activarDesactivarUsuario.desactivar(id);
    }

    // ======================
    // LISTAR USUARIOS
    // ======================
    @GetMapping
    public List<UsuarioDtoResponse> listar() {
        return consultarUsuarios.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // ======================
    // LOGIN (INICIAR SESIÓN)
    // ======================
    @PostMapping("/login")
    public UsuarioDtoResponse login(@RequestParam String email) {
        Usuario usuario = iniciarSesion.ejecutar(email);
        return mapper.toResponse(usuario);
    }

    // ======================
    // VALIDAR ROL
    // ======================
    @GetMapping("/{id}/rol")
    public Map<String, Boolean> validarRol(@PathVariable Long id) {
        return Map.of(
                "admin", validarRolUsuario.esAdmin(id),
                "laboratorista", validarRolUsuario.esLaboratorista(id)
        );
    }

    // ======================
    // AUDITORÍA DE USUARIO
    // ======================
    @GetMapping("/{id}/auditoria")
    public List<AuditLog> verAuditoria(@PathVariable Long id) {
        return verAccionesUsuario.ejecutar(id);
    }
}

