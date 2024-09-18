package com.jefiro.magnetMovie.domain.movies.dto;

import com.jefiro.magnetMovie.domain.movies.Movies;

import java.util.List;

public record MoviesDetalhesDTO(
        int id,
        int vote_count,
        double popularity,
        String vote_average,
        boolean video,
        boolean adult,
        String backdrop_path,
        String original_language,
        String original_title,
        String overview,
        String poster_path,
        String release_date,
        String title,
        int runtime,
        List<GeneroDTO> genero
) {}
