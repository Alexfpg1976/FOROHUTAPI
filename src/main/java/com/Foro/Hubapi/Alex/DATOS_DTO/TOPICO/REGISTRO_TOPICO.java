package com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record REGISTRO_TOPICO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje ,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId) {
}
