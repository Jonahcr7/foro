package com.alura.foro.domain.usuario;

import com.alura.foro.domain.perfil.Perfil;

import java.util.List;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email,
        String password,
        List<Perfil> perfiles
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getPerfiles()
        );
    }
}
