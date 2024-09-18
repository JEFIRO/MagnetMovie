package com.jefiro.magnetMovie.domain.usuario;

import java.time.LocalDate;

public record UsuarioCadastro(
        String name,
        String userName,
        String email,
        LocalDate date,
        String password
) {
}
