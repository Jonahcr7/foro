package com.alura.foro.domain.perfil;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Perfil")
@Table(name = "perfil")
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Perfil(DatosRegistroPerfil datos) {
        this.id = null;
        this.nombre = datos.nombre();
    }
}
