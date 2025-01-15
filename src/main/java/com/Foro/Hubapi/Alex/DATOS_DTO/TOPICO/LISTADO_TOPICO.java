package com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO;

import com.Foro.Hubapi.Alex.MODELOS.TOPICO;

public record LISTADO_TOPICO(Long id, String titulo, String mensaje, String autor, String curso) {

    public LISTADO_TOPICO(TOPICO topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }
}
