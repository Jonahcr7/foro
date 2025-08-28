package com.alura.foro.controller;

import com.alura.foro.domain.respuesta.DatosDetalleRespuesta;
import com.alura.foro.domain.respuesta.DatosRegistroRespuesta;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.respuesta.RespuestaRepository;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRepository;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity crearRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder) {
        var topicoId = topicoRepository.findById(datos.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado o registrado en la BD"));
        var usuarioId = usuarioRepository.findById(datos.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        var respuesta = new Respuesta(datos, topicoId, usuarioId);
        repository.save(respuesta);
        var uri = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }
}
