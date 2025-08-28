package com.alura.foro.domain.topico;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Status status,
        String usuario,
        String curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
    }

}
