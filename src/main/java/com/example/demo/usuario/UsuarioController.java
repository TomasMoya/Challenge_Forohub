package com.example.demo.usuario;

import com.example.demo.curso.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
// @SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsuarioRepository usuarioRepositorio;
    @Autowired
    private CursoRepository cursoRepositorio;

    @Transactional
    @PostMapping
    public ResponseEntity agregarUsuario(@RequestBody @Valid DatosUsuarioDTO datosUsuario, UriComponentsBuilder uriComponentsBuilder) {
        var curso = cursoRepositorio.findById(datosUsuario.curso_id()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        var usuario = usuarioRepositorio.save(new Usuario(datosUsuario, curso));
        var detalleUsuario = new DetalleUsuarioDTO(datosUsuario.nombre(), datosUsuario.email());

        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(detalleUsuario);
    }

    @GetMapping
    public ResponseEntity mostrarUsuarios(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(usuarioRepositorio.findByEstadoTrue(pageable).map(u -> new DetalleUsuarioDTO(u.getNombre(), u.getEmail())));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deshabilitarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepositorio.getReferenceById(id);
        usuario.deshabilitarUsuario();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var token = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());
        var autenticacion = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
