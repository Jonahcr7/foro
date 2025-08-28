package com.alura.foro.controller;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.curso.DatosDetalleCurso;
import com.alura.foro.domain.curso.DatosRegistroCurso;
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
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity crearCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder) {
        var curso = new Curso(datos);
        repository.save(curso);
        var uri = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }
}
