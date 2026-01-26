package com.example.demo.infra.autenticacion;

import com.example.demo.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        return usuarioRepositorio.findByNombre(nombre);
    }
}
