package com.example.demo.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuarioDTO(
        @NotBlank String nombre,
        @NotBlank String contrasena,
        @NotBlank @Email String email,
        @NotNull Long curso_id
)
{}
