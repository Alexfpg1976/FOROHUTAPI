package com.Foro.Hubapi.Alex.DATOS_DTO.CURSO;

import com.Foro.Hubapi.Alex.MODELOS.CURSO;

public record RESPUESTA_CURSO(String nombre, String categoria) {

    public RESPUESTA_CURSO(CURSO curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
