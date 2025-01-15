package com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO;

import com.Foro.Hubapi.Alex.MODELOS.ESTADO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ACTUALIZAR_TOPICO(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        ESTADO estado,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId) {
}
