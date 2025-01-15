package com.Foro.Hubapi.Alex.MODELOS;

import com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO.ACTUALIZAR_TOPICO;
import com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO.REGISTRO_TOPICO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name= "topicos")
@Entity(name="TOPICO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TOPICO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private ESTADO estado = ESTADO.NO_RESPONDIDO;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private USUARIO autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private CURSO curso;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private List<RESPUESTA> RESPUESTAS = new ArrayList<>();

    public TOPICO(REGISTRO_TOPICO datos, USUARIO autor, CURSO curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarDatos(ACTUALIZAR_TOPICO datosActualizar, USUARIO autor, CURSO curso) {
        if (datosActualizar.titulo() != null) {
            this.titulo = datosActualizar.titulo();
        }
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
        }
        if (datosActualizar.estado() != datosActualizar.estado()) {
            this.estado = datosActualizar.estado();
        }
        if (autor != null) {
            this.autor = autor;
        }
        if (curso != null) {
            this.curso = curso;
        }
    }

    public void agregarRespuesta(RESPUESTA respuesta) {
        this.RESPUESTAS.add(respuesta);
        if (respuesta.getSolucion()) {
            this.estado = ESTADO.SOLUCIONADO;
        } else {
            this.estado = ESTADO.NO_SOLUCIONADO;
        }
    }

    public void cerrarTopico() {
        this.estado = ESTADO.CERRADO;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }
}