package com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ACTUALIZAR_RESPUESTA(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotBlank
        Long autorId,
        @NotNull
        Boolean solucion) {
}
