package com.example.demo.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCursoDTO(
        @NotBlank String curso
) {
}
