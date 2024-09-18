package com.jefiro.magnetMovie.domain.movies.dto;

import java.util.List;

public record MoviesByActor(
        List<ResumeDetalhes> known_for
) {
}
