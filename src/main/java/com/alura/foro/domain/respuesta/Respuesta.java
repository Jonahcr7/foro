package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;
    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    private  Boolean solucion;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico, Usuario usuario) {
        this.id = null;
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
        this.solucion = Boolean.FALSE;
    }
}
