package com.jefiro.magnetMovie.domain.movies.dto;

public record ResumeDetalhes(
        int id,
        String title,
        String poster_path,
        double vote_average) {
}
