package com.alura.foro.domain.topico;

import com.alura.foro.domain.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DatosListaTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Status status,
        Long usuarioId,
        Long cursoId
) {
    public DatosListaTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()
        );
    }
}
