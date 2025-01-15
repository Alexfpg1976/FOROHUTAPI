package com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO;

import com.Foro.Hubapi.Alex.DATOS_DTO.CURSO.RESPUESTA_CURSO;
import com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO.RESPUESTA_USUARIO1;
import com.Foro.Hubapi.Alex.MODELOS.TOPICO;

public record RESPUESTA_TOPICO2(Long id, String titulo, String mensaje, String fechaCreacion, String estado, RESPUESTA_USUARIO1 autor,
                                RESPUESTA_CURSO curso) {

    public RESPUESTA_TOPICO2(TOPICO topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(), new RESPUESTA_USUARIO1(topico.getAutor()),
                new RESPUESTA_CURSO(topico.getCurso()));
    }
}
