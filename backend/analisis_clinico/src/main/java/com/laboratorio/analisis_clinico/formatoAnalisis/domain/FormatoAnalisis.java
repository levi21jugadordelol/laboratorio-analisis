package com.laboratorio.analisis_clinico.formatoAnalisis.domain;


import com.laboratorio.analisis_clinico.analisis.domain.Analisis;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.enume.EstadoFormato;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "formato_analisis")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FormatoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormatoAnalisis;



    @Column(nullable = false, length = 150)
    private String nombreFormato;

    @Column(length = 255)
    private String descripcion;

    /**
     * Define la estructura clínica del resultado.
     * El dominio no valida su esquema, solo exige que exista.
     */
    @Column(nullable = false, columnDefinition = "json")
    private Map<String, Object> estructuraFormato;

    /**
     * Versionado del formato.
     * Controlado por el dominio.
     */
    @Column(nullable = false)
    private double version;

    /**
     * Usuario responsable de la creación.
     */
    @Column(name = "created_by", nullable = false,updatable = false)
    private Long createdByUsuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoFormato estadoFormato;


    @ManyToOne
    @JoinColumn(name="analisis_id",nullable = false)
    private Analisis analisis;

    @OneToMany
            (mappedBy = "formatoAnalisis",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<OrdenAnalisis> listaOrdenes = new HashSet<>();

    public void addOrdenAnalisis(OrdenAnalisis ordenAnalisis){
        this.listaOrdenes.add(ordenAnalisis);
        ordenAnalisis.asignarFormato(this);
    }

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    public void asignarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException(
                    "El usuario no puede ser nulo."
            );
        }
        if (this.usuario != null) {
            throw new IllegalStateException(
                    "La orden ya está asociada a un usuario."
            );
        }
        this.usuario = usuario;
    }

    // =========================
    // CONSTRUCTOR DE DOMINIO
    // =========================

    public FormatoAnalisis(
            String nombreFormato,
            String descripcion,
            Map<String, Object> estructuraFormato,
            Long createdByUsuario
    ) {
        setNombreFormato(nombreFormato);
        validarEstructura(estructuraFormato);
        validarUsuario(createdByUsuario);

        this.descripcion = normalizar(descripcion);
        this.estructuraFormato = estructuraFormato;
        this.createdByUsuario = createdByUsuario;
        this.version = 1.0;
        this.estadoFormato = EstadoFormato.VIGENTE;
        this.fechaCreacion = LocalDateTime.now();
    }


    // =========================
    // REGLAS DE DOMINIO
    // =========================

    /**
     * Un formato vigente puede volverse obsoleto,
     * pero nunca se elimina.
     */
    public void marcarComoObsoleto() {
        if (this.estadoFormato == EstadoFormato.OBSOLETO) {
            throw new IllegalStateException(
                    "El formato ya se encuentra obsoleto."
            );
        }
        this.estadoFormato = EstadoFormato.OBSOLETO;
    }

    /**
     * Crea una nueva versión del formato.
     * El formato actual queda obsoleto.
     */
    public FormatoAnalisis crearNuevaVersion(
            Map<String, Object> nuevaEstructura,
            Long usuarioId
    ) {
        if (this.estadoFormato != EstadoFormato.VIGENTE) {
            throw new IllegalStateException(
                    "Solo se puede versionar un formato vigente."
            );
        }

        validarEstructura(nuevaEstructura);
        validarUsuario(usuarioId);

        this.marcarComoObsoleto();

        return new FormatoAnalisis(

                this.nombreFormato,
                this.descripcion,
                nuevaEstructura,
                usuarioId
        ).conVersion(this.version + 1);
    }

    public void asignarAnalisis(Analisis analisis) {
        if (this.analisis != null) {
            throw new IllegalStateException(
                    "El formato ya está asociado a un análisis."
            );
        }
        if (analisis == null) {
            throw new IllegalArgumentException(
                    "El formato debe pertenecer a un análisis."
            );
        }
        this.analisis = analisis;
    }




    // =========================
    // CONSULTAS DE DOMINIO
    // =========================

    public boolean estaVigente() {
        return this.estadoFormato == EstadoFormato.VIGENTE;
    }

    public boolean estaObsoleto() {
        return this.estadoFormato == EstadoFormato.OBSOLETO;
    }

    // =========================
    // INVARIANTES INTERNAS
    // =========================

    private void setNombreFormato(String nombreFormato) {
        String n = normalizar(nombreFormato);

        if (n == null || n.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del formato es obligatorio."
            );
        }

        if (n.length() > 150) {
            throw new IllegalArgumentException(
                    "El nombre del formato excede el máximo permitido (150)."
            );
        }

        this.nombreFormato = n;
    }


    private void validarEstructura(Map<String, Object> estructura) {
        if (estructura == null || estructura.isEmpty()) {
            throw new IllegalArgumentException(
                    "La estructura del formato es obligatoria."
            );
        }
    }

    private void validarUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException(
                    "El usuario creador del formato es obligatorio."
            );
        }
    }

    private FormatoAnalisis conVersion(double nuevaVersion) {
        this.version = nuevaVersion;
        return this;
    }
    public void actualizarDatos(String nombreFormato, String descripcion) {
        setNombreFormato(nombreFormato);
        this.descripcion = normalizar(descripcion);
    }


    private String normalizar(String valor) {
        return (valor == null) ? null : valor.trim();
    }


}

