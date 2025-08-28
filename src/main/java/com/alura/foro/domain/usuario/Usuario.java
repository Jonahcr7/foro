package com.alura.foro.domain.usuario;

import com.alura.foro.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "Usuario")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfiles = new ArrayList<>();

    public Usuario(DatosRegistroUsuario datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.password = datos.password();
        this.perfiles = datos.perfil();
    }
}

