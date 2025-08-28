package com.alura.foro.controller;

import com.alura.foro.domain.usuario.DatosDetalleUsuario;
import com.alura.foro.domain.usuario.DatosRegistroUsuario;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datos);
        usuarioRepository.save(usuario);
        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

}
