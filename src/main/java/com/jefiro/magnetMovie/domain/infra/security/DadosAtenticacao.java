package com.jefiro.magnetMovie.domain.infra.security;

public record DadosAtenticacao(
        String email,
        String senha
) {
}
