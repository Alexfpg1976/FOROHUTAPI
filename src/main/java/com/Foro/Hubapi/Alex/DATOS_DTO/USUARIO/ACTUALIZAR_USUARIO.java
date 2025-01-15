package com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO;

import com.Foro.Hubapi.Alex.MODELOS.TIPO;

public record ACTUALIZAR_USUARIO(Long id, String nombre, String email, String contrasena, TIPO tipo) {
}
