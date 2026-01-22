package com.example.demo.topico;

import com.example.demo.usuario.DetalleUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
// @SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private AgregarTopico agregarTopico;
    @Autowired
    private TopicoRepository topicoRepositorio;

    @Transactional
    @PostMapping
    public ResponseEntity agregarTopico(@RequestBody @Valid DatosTopicoDTO datosTopico, UriComponentsBuilder uriComponentsBuilder){
        var topicoAgregado = agregarTopico.agregarTopico(datosTopico, uriComponentsBuilder);

        return ResponseEntity.created(topicoAgregado).body(new DetalleTopicoDTO(datosTopico.titulo(), datosTopico.mensaje()));
    }

    @GetMapping
    public ResponseEntity mostrarTopicos(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(topicoRepositorio.findAll(pageable).map(t -> new DatosTopicoDTO(t.getTitulo(), t.getMensaje(), t.getEstado(), t.getUsuario().getId(), t.getCurso().getId())));
    }

    @GetMapping("/recientes")
    public ResponseEntity mostrarTopicosRecientes(){
        return ResponseEntity.ok(topicoRepositorio.findTop10OrderByFechaDeCreacion().stream().map(t -> new DatosTopicoDTO(t.getTitulo(), t.getMensaje(), t.getEstado(), t.getUsuario().getId(), t.getCurso().getId())));
    }
}
