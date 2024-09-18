package com.jefiro.magnetMovie.domain.movies.dto;

import java.util.List;

public record CreditosDTO(
        List<AtoresDTO> cast,
        List<DiretorDTO> crew) {
}
