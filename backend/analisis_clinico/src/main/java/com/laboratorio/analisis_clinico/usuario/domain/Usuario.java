package com.laboratorio.analisis_clinico.usuario.domain;

import com.laboratorio.analisis_clinico.auditlog.domain.AuditLog;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import com.laboratorio.analisis_clinico.usuario.domain.enume.EstadoUsuario;
import com.laboratorio.analisis_clinico.usuario.domain.enume.RolUsuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nombreUsuario;

    @Column(nullable = false, length = 150)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolUsuario rolUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EstadoUsuario estadoUsuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany
            (mappedBy = "usuario",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true)
    private Set<Orden> listaOrden = new HashSet<>();

    public void agregarOrden(Orden orden) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser nula.");
        }
        this.listaOrden.add(orden);
        orden.asignarUsuario(this);
    }

    @OneToMany
            (mappedBy = "usuario",
            fetch = FetchType.LAZY,
                    cascade = { CascadeType.PERSIST, CascadeType.MERGE }
                    ,
            orphanRemoval = true)
    private Set<FormatoAnalisis> listaFormatoAnalisis = new HashSet<>();

    public void agregarFormatoAnalisis(FormatoAnalisis formatoAnalisis){
        if(formatoAnalisis == null){
            throw new IllegalArgumentException("el formato no puede ser nula");
        }
        this.listaFormatoAnalisis.add(formatoAnalisis);
        formatoAnalisis.asignarUsuario(this);
    }

    @OneToMany
            (mappedBy = "usuario",
            fetch = FetchType.LAZY,
                    cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = false)
    private Set<Resultado> listaResultado = new HashSet<>();

    public void agregarResultado(Resultado resultado){
        if(resultado==null){
            throw new IllegalArgumentException("el resultado no puede ser null");
        }
        this.listaResultado.add(resultado);
        resultado.asignarUsuario(this);
    }

    @OneToMany
            (mappedBy = "usuario",
            fetch = FetchType.LAZY,
                    cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = false)
    private Set<Reporte> listaReporte = new HashSet<>();

    public void agregarReporte(Reporte reporte){
        if(reporte == null){
            throw new IllegalArgumentException("el reporte no puede ser null");
        }
        this.listaReporte.add(reporte);
        reporte.asignarUsuario(this);
    }

    @OneToMany
            (mappedBy = "usuario",
            fetch = FetchType.LAZY,
                    cascade = { CascadeType.PERSIST, CascadeType.MERGE },
                    orphanRemoval = false
            )
    private Set<AuditLog> listaAuditoria = new HashSet<>();

    public void agregarAuditoria(AuditLog auditLog){
        if(auditLog==null){
            throw  new IllegalArgumentException("la duiditoria noi puede ser nulla");
        }
        this.listaAuditoria.add(auditLog);
        auditLog.asignarUsuario(this);

    }

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public Usuario(
            String nombreUsuario,
            String email,
            RolUsuario rolUsuario
    ) {
        validarNombre(nombreUsuario);
        validarEmail(email);
        validarRol(rolUsuario);

        this.nombreUsuario = normalizar(nombreUsuario);
        this.email = normalizar(email);
        this.rolUsuario = rolUsuario;
        this.estadoUsuario = EstadoUsuario.ACTIVO; // regla de creación
        this.fechaCreacion = LocalDateTime.now();
    }

    // =========================
    // REGLAS DE DOMINIO
    // =========================

    public void actualizarDatos(String nombreUsuario, String email) {
        validarNombre(nombreUsuario);
        validarEmail(email);

        this.nombreUsuario = normalizar(nombreUsuario);
        this.email = normalizar(email);
    }

    public void activar() {
        if (this.estadoUsuario == EstadoUsuario.ACTIVO) {
            throw new IllegalStateException(
                    "El usuario ya se encuentra activo."
            );
        }
        this.estadoUsuario = EstadoUsuario.ACTIVO;
    }

    public void desactivar() {
        if (this.estadoUsuario == EstadoUsuario.INACTIVO) {
            throw new IllegalStateException(
                    "El usuario ya se encuentra inactivo."
            );
        }
        this.estadoUsuario = EstadoUsuario.INACTIVO;
    }

    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaActivo() {
        return this.estadoUsuario == EstadoUsuario.ACTIVO;
    }

    public boolean esAdmin() {
        return this.rolUsuario == RolUsuario.ADMIN;
    }

    public boolean esLaboratorista() {
        return this.rolUsuario == RolUsuario.LABORATORISTA;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del usuario es obligatorio."
            );
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El email del usuario es obligatorio."
            );
        }
    }

    private void validarRol(RolUsuario rolUsuario) {
        if (rolUsuario == null) {
            throw new IllegalArgumentException(
                    "El rol del usuario es obligatorio."
            );
        }
    }

    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }
}

