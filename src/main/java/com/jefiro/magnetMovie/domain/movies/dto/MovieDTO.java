package com.jefiro.magnetMovie.domain.movies.dto;

import com.jefiro.magnetMovie.domain.movies.Movies;

public record MovieDTO(
        int id,
        String title,
        String originalLanguage,
        String originalTitle,
        String backdropPath,
        String overview,
        String posterPath,
        String releaseDate,
        String voteAverage) {
    public MovieDTO(Movies movies) {
        this(movies.getId(), movies.getTitle(), movies.getOriginalLanguage(), movies.getOriginalTitle(),
                movies.getBackdropPath(), movies.getOverview(), movies.getPosterPath(), movies.getReleaseDate(),
                movies.getVoteAverage());
    }
}
