package com.jefiro.magnetMovie.domain.movies.dto;

import java.util.List;

public record DadosMoviesActor(
        List<MoviesByActor> results
) {
}
