package com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record REGISTRO_RESPUESTA(
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        Long autorId,
        @NotBlank
        Boolean solucion) {
}
