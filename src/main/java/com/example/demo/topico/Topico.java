package com.example.demo.topico;

import com.example.demo.curso.Curso;
import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(DatosTopicoDTO datos, Curso curso, Usuario usuario) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.estado = datos.estado();
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico, Curso curso) {
        if (datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.estado() != null){
            this.estado = datosActualizarTopico.estado();
        }
        if (datosActualizarTopico.curso_id() != null){
            this.curso = curso;
        }
    }

    public void deshabilitarTopico() {
        this.estado = Estado.ELIMINADO;
    }
}