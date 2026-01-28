package com.example.demo.topico;

import com.example.demo.curso.Curso;
import com.example.demo.infra.exceptions.TopicoException;
import com.example.demo.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private FuncionesTopico funcionesTopico;
    @Autowired
    private TopicoRepository topicoRepositorio;

    @Transactional
    @PostMapping
    public ResponseEntity agregarTopico(@RequestBody @Valid DatosTopicoDTO datosTopico, UriComponentsBuilder uriComponentsBuilder){
        var topicoAgregado = funcionesTopico.agregarTopico(datosTopico, uriComponentsBuilder);

        return ResponseEntity.created(topicoAgregado).body(new TopicoCreadoDTO(datosTopico.titulo(), datosTopico.mensaje()));
    }

    @GetMapping
    public ResponseEntity mostrarTopicos(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(topicoRepositorio.findByEstadoNoEliminado(pageable).map(t -> new DatosTopicoDTO(t.getTitulo(), t.getMensaje(), t.getEstado(), t.getUsuario().getId(), t.getCurso().getId())));
    }

    @GetMapping("/recientes")
    public ResponseEntity mostrarTopicosRecientes(){
        return ResponseEntity.ok(topicoRepositorio.findTop10OrderByFechaDeCreacion().stream().map(t -> new DatosTopicoDTO(t.getTitulo(), t.getMensaje(), t.getEstado(), t.getUsuario().getId(), t.getCurso().getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopicoEspecificoConId(@PathVariable Long id){
        var topicoEncontrado = funcionesTopico.mostrarTopicoPorId(id);
        return ResponseEntity.ok().body(topicoEncontrado);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topicoActualizado = funcionesTopico.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(topicoActualizado);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deshabilitarTopico(@PathVariable Long id){
        Topico topico = topicoRepositorio.getReferenceById(id);
        topico.deshabilitarTopico();
        return ResponseEntity.noContent().build();
    }
}
