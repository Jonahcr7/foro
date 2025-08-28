package com.alura.foro.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(
        @NotBlank String nombre
) {
}
