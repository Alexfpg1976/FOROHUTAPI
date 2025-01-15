package com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AUTENTICACION_USUARIO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena) {
}
