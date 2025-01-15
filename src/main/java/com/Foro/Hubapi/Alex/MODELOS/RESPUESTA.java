package com.Foro.Hubapi.Alex.MODELOS;

import com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA.ACTUALIZAR_RESPUESTA;
import com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA.REGISTRO_RESPUESTA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "respuestas")
@Entity(name = "RESPUESTA")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RESPUESTA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private TOPICO topico;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private USUARIO autor;
    private Boolean solucion = false;

    public RESPUESTA(REGISTRO_RESPUESTA datos, TOPICO topico, USUARIO autor) {
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.autor = autor;
        this.solucion = datos.solucion();
        if (datos.solucion()) {
            this.topico.setEstado(ESTADO.SOLUCIONADO);
        } else {
            this.topico.setEstado(ESTADO.NO_SOLUCIONADO);
        }
    }

    public void actualizarDatos(ACTUALIZAR_RESPUESTA datosActualizar, TOPICO topico, USUARIO autor) {
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
        }
        if (topico != null) {
            this.topico = topico;
        }
        if (autor != null) {
            this.autor = autor;
        }
        if (datosActualizar.solucion() != this.solucion) {
            this.solucion = datosActualizar.solucion();
        }
    }
}
