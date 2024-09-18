
package com.jefiro.magnetMovie.domain.movies;

import com.jefiro.magnetMovie.domain.movies.dto.GeneroDTO;

import java.util.List;

public record DadosMovies(
        int id,
        int vote_count,
        double popularity,
        double vote_average,
        boolean video,
        boolean adult,
        String backdrop_path,
        String original_language,
        String original_title,
        String overview,
        String poster_path,
        String release_date,
        int runtime,
        List<GeneroDTO> genres,
        String title
) {}