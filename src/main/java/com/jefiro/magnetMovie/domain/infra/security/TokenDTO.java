package com.jefiro.magnetMovie.domain.infra.security;

public record TokenDTO(
        String token,
        String idInterno
) {
}
