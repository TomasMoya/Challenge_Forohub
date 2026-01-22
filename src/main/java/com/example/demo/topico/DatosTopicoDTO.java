package com.example.demo.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Estado estado,
        @NotNull Long usuario_id,
        @NotNull Long curso_id
) {
}
