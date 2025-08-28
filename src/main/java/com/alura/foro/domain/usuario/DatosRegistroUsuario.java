package com.alura.foro.domain.usuario;

import com.alura.foro.domain.perfil.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @NotNull @Email String email,
        @NotBlank String password,
        @NotEmpty List<Perfil> perfil
) {
}