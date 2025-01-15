package com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO;

import com.Foro.Hubapi.Alex.MODELOS.USUARIO;

public record RESPUESTA_USUARIO1(String nombre, String email, String tipo) {

    public RESPUESTA_USUARIO1(USUARIO usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
