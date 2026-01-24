package com.example.demo.topico;

import com.example.demo.curso.Curso;
import com.example.demo.curso.DatosCursoDTO;
import com.example.demo.usuario.DetalleUsuarioDTO;
import com.example.demo.usuario.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DetalleTopicoDTO(
        String titulo,
        String mensaje,
        String fechaDeCreacion,
        Estado estado,
        DetalleUsuarioDTO usuario,
        DatosCursoDTO curso) {

    static DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DetalleTopicoDTO(Topico t, Usuario u, Curso c) {
        this(
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaDeCreacion().format(formatoHorario),
                t.getEstado(),
                new DetalleUsuarioDTO(u.getNombre(), u.getEmail()),
                new DatosCursoDTO(c.getCurso()));
    }
}
