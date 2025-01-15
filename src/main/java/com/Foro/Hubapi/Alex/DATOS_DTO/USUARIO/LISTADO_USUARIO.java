package com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO;

import com.Foro.Hubapi.Alex.MODELOS.USUARIO;

public record LISTADO_USUARIO(Long id, String nombre, String email, String tipo) {

    public LISTADO_USUARIO(USUARIO usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}
