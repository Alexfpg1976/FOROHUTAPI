package com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO;

import com.Foro.Hubapi.Alex.MODELOS.TIPO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record REGISTRO_USUARIO(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena,
        @NotNull
        TIPO tipo) {
}
