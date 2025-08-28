package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String topico,
        LocalDateTime fechaDeCreacion,
        String usuario,
        Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre(),
                respuesta.getSolucion()
        );
    }
}
