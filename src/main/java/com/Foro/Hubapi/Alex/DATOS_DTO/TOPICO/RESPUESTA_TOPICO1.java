package com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO;

import com.Foro.Hubapi.Alex.MODELOS.TOPICO;

public record RESPUESTA_TOPICO1(String titulo, String mensaje, String autor, String curso) {

    public RESPUESTA_TOPICO1(TOPICO topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
