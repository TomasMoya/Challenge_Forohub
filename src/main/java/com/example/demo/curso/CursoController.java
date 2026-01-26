package com.example.demo.curso;

import com.example.demo.usuario.DetalleUsuarioDTO;
import com.example.demo.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
// @SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepositorio;

    @Transactional
    @PostMapping
    public ResponseEntity agregarCursos(@RequestBody @Valid DatosCursoDTO datosCurso, UriComponentsBuilder uriComponentsBuilder)  {
        var curso = cursoRepositorio.save(new Curso(datosCurso));


        URI uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body("El curso '" + datosCurso.curso() + "' se creó con éxito");
    }

    @GetMapping
    public ResponseEntity mostrarCursos(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(cursoRepositorio.findByEstadoTrue(pageable).map(c -> new DatosCursoDTO(c.getCurso())));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deshabilitarCurso(@PathVariable Long id){
        Curso curso = cursoRepositorio.getReferenceById(id);
        curso.deshabilitarUsuario();
        return ResponseEntity.noContent().build();
    }
}
