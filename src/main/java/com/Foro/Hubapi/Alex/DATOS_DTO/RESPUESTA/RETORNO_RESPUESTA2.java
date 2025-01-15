package com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA;

import com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO.RESPUESTA_TOPICO1;
import com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO.RESPUESTA_USUARIO1;
import com.Foro.Hubapi.Alex.MODELOS.RESPUESTA;

public record RETORNO_RESPUESTA2(String mensaje, RESPUESTA_TOPICO1 topico, String fechaCreacion, RESPUESTA_USUARIO1 autor, String solucion) {

    public RETORNO_RESPUESTA2(RESPUESTA respuesta) {
        this(respuesta.getMensaje(), new RESPUESTA_TOPICO1(respuesta.getTopico()), respuesta.getFechaCreacion().toString(),
                new RESPUESTA_USUARIO1(respuesta.getAutor()), respuesta.getSolucion().toString());
    }
}
