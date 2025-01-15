package com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO;

import com.Foro.Hubapi.Alex.MODELOS.USUARIO;

public record RESPUESTA_USUARIO2(String nombre, String email, String contrasena, String tipo) {

    public RESPUESTA_USUARIO2(USUARIO usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getTipo().toString());
    }
}
