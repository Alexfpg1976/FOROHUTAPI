package com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA;

import com.Foro.Hubapi.Alex.MODELOS.RESPUESTA;

public record RETORNO_RESPUESTA1(String mensaje, String topico, String autor) {

    public RETORNO_RESPUESTA1(RESPUESTA respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
