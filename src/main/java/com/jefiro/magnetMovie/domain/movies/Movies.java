package com.jefiro.magnetMovie.domain.movies;

import com.jefiro.magnetMovie.domain.movies.dto.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movies {
    private int id;
    private int voteCount;
    private double popularity;
    private String voteAverage;
    private boolean video;
    private boolean adult;
    private String backdropPath;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private List<AtoresDTO> ator;
    private DiretorDTO direto;
    private String title;
    private int runtime;

    public Movies(DadosMovies dadosMovies) {
        this.adult = dadosMovies.adult();
        this.backdropPath = dadosMovies.backdrop_path();
        this.id = dadosMovies.id();
        this.originalLanguage = dadosMovies.original_language();
        this.originalTitle = dadosMovies.original_title();
        this.overview = dadosMovies.overview();
        this.popularity = dadosMovies.popularity();
        this.posterPath = dadosMovies.poster_path();
        this.releaseDate = dadosMovies.release_date();
        this.title = dadosMovies.title();
        this.video = dadosMovies.video();
        this.voteAverage = String.format("%.1f", dadosMovies.vote_average());
        this.voteCount = dadosMovies.vote_count();
    }

}

