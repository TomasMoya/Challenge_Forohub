package com.example.demo.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Estado estado,
        Long curso_id
) {
}
