package com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA;

import com.Foro.Hubapi.Alex.MODELOS.RESPUESTA;

public record LISTADO_RESPUESTA(Long id, String mensaje, String topico, String autor) {

    public LISTADO_RESPUESTA(RESPUESTA respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}
