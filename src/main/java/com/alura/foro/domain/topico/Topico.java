package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaDeCreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;
        this.usuario = usuario;
        this.curso = curso;
        this.respuestas = null;
    }

    public void actualizarDatos(@Valid DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }
}
