package com.example.demo.usuario;

import com.example.demo.curso.Curso;
import com.example.demo.curso.CursoRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Usuario(@Valid DatosUsuarioDTO datosUsuario, Curso curso) {
        this.nombre = datosUsuario.nombre();
        this.email = datosUsuario.email();
        this.contrasena = datosUsuario.contrasena();
        this.estado = true;
        this.curso = curso;
    }
}
