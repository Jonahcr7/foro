package com.alura.foro.controller;

import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.perfil.PerfilRepository;
import com.alura.foro.domain.topico.*;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity crearTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuarioId = usuarioRepository.findById(datos.usuarioId()).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        var cursoId = cursoRepository.findById(datos.cursoId()).orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        var topico = new Topico(datos, usuarioId, cursoId);
        repository.save(topico);
        var uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity <Page<DatosListaTopicos>> mostrarTopicos(@PageableDefault(size = 10, sort = {"fechaDeCreacion"}) Pageable pageable) {
        var page = repository.mostrarTodosLosTopicos(pageable).map(DatosListaTopicos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

//    @Transactional
//    @PutMapping("/{id}")
//    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
//        var topico = repository.findById(id);
//        if (topico.isPresent()) {
//            var topicoId = topico.get();
//            topicoId.actualizarDatos(datos);
//            return ResponseEntity.ok(new DatosDetalleTopico(topicoId));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // Mismo código que el de arriba pero usando orElseThrow()
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        var topico = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el ID"));
        topico.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

// Otra forma de eliminar topicos utilizando el .isPresent()
//    @Transactional
//    @DeleteMapping("/{id}")
//    public ResponseEntity eliminarTopico(@PathVariable Long id) {
//        var topico = repository.findById(id);
//        if (topico.isPresent()) {
//            repository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }



}
