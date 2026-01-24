package com.example.demo.topico;

import com.example.demo.curso.Curso;
import com.example.demo.curso.CursoRepository;
import com.example.demo.infra.exceptions.TopicoException;
import com.example.demo.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class FuncionesTopico {
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
            throw new TopicoException("Ya existe un tópico con ese nombre y mensaje");
        }

        var topico = topicoRepositorio.save(new Topico(datosTopico, curso, usuario));
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return uri;
    }

    public DetalleTopicoDTO mostrarTopicoPorId(Long id){
        boolean idValido = topicoRepositorio.existsById(id);
        if (!idValido) {
            throw new TopicoException("No existe un tópico con el id indicado");
        }
        var topico = topicoRepositorio.getReferenceById(id);

        var usuario = usuarioRepositorio.findById(topico.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        var curso = cursoRepositorio.findById(topico.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        DetalleTopicoDTO detalleTopico = new DetalleTopicoDTO(topico, usuario, curso);
        return detalleTopico;
    }

    public DetalleTopicoDTO actualizarTopico(DatosActualizarTopico datosActualizarTopico){
        var topicoExiste = topicoRepositorio.existsById(datosActualizarTopico.id());
        if (!topicoExiste){
            throw new TopicoException("No existe ningún tópico con el id indicado");
        }
        var topico = topicoRepositorio.getReferenceById(datosActualizarTopico.id());
        var usuario = usuarioRepositorio.findById(topico.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso;
        if (datosActualizarTopico.curso_id() != null){
            curso = cursoRepositorio.findById(datosActualizarTopico.curso_id())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        }else {
            curso = topico.getCurso();
        }


        topico.actualizarTopico(datosActualizarTopico, curso);
        DetalleTopicoDTO detalleTopico = new DetalleTopicoDTO(topico, usuario, curso);
        return detalleTopico;
    }
}
