package com.jefiro.magnetMovie.domain.movies.dto;

public record AtoresDTO(
        String id,
        String name,
        String original_name,
        String profile_path,
        String character,
        String credit_id,
        String order
) {
}
