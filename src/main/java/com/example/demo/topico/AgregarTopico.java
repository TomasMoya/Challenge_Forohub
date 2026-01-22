package com.example.demo.topico;

import com.example.demo.curso.CursoRepository;
import com.example.demo.infra.exceptions.CreacionTopicoException;
import com.example.demo.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AgregarTopico {
    @Autowired
    private TopicoRepository topicoRepositorio;
    @Autowired
    private CursoRepository cursoRepositorio;
    @Autowired
    private UsuarioRepository usuarioRepositorio;

    public URI agregarTopico(DatosTopicoDTO datosTopico, UriComponentsBuilder uriComponentsBuilder){
        var curso = cursoRepositorio.findById(datosTopico.curso_id()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        var usuario = usuarioRepositorio.findById(datosTopico.usuario_id()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        boolean topicoYaExiste = topicoRepositorio.existsByTituloAndMensaje(datosTopico.titulo(), datosTopico.mensaje());

        if (topicoYaExiste) {
            throw new CreacionTopicoException("Ya existe un tópico con ese nombre y mensaje");
        }

        var topico = topicoRepositorio.save(new Topico(datosTopico, curso, usuario));
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return uri;
    }
}
