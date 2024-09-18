package com.jefiro.magnetMovie.domain.movies;

import com.jefiro.magnetMovie.domain.movies.dto.TraillerDTO;

import java.util.List;

public record DadosTrailler(
        List<TraillerDTO> results
) {
}
