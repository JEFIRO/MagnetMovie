package com.jefiro.magnetMovie.domain.movies;

import java.util.List;

public record MoviesTransfer(
        List<DadosMovies> results,
        String page
) {
}
