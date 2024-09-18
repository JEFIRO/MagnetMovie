package com.jefiro.magnetMovie.domain.movies.dto;

import com.jefiro.magnetMovie.domain.movies.DadosMovies;

import java.util.List;

public record DetalhesDTO(
        DadosMovies movie,
        List<MovieDTO> relacionados,
        List<AtoresDTO> actors,
        DiretorDTO director,
        List<TraillerDTO> trailers
) {

}
