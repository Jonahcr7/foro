package com.alura.foro.controller;

import com.alura.foro.domain.perfil.DatosDetallePerfil;
import com.alura.foro.domain.perfil.DatosRegistroPerfil;
import com.alura.foro.domain.perfil.Perfil;
import com.alura.foro.domain.perfil.PerfilRepository;
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
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity crearPerfil(@RequestBody @Valid DatosRegistroPerfil datos, UriComponentsBuilder uriComponentsBuilder) {
         var perfil = new Perfil(datos);
         repository.save(perfil);
         var uri = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
         return ResponseEntity.created(uri).body(new DatosDetallePerfil(perfil));
    }
}
