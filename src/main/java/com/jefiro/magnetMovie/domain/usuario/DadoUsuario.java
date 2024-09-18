package com.jefiro.magnetMovie.domain.usuario;

import java.time.LocalDate;

public record DadoUsuario(
        String nome,
        String userName,
        String Email,
        LocalDate dataNascimento,
        String senha
) {
}
