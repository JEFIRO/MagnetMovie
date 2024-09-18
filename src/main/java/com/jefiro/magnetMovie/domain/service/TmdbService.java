package com.jefiro.magnetMovie.domain.service;

import com.jefiro.magnetMovie.domain.movies.*;
import com.jefiro.magnetMovie.domain.movies.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TmdbService {

    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "8d774839142d9e39fa3b87d7d8885943";
    private final String urlF = "https://api.themoviedb.org/3/movie/";

    public List<MovieDTO> getPopularMovies(int page) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/movie/popular")
                .queryParam("page", page)
                .queryParam("language", "pt-BR")
                .queryParam("api_key", apiKey)
                .toUriString();
        System.out.println(url);

        MoviesTransfer dados = restTemplate.getForObject(url, MoviesTransfer.class);

        assert dados != null;
        return converterDTOList(dados);
    }

    public DetalhesDTO getDetalhes(int id) {
        String url = UriComponentsBuilder.fromHttpUrl(urlF + id)
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .toUriString();
        String url2 = UriComponentsBuilder.fromHttpUrl(urlF + id + "/credits?")
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .toUriString();
        String url3 = UriComponentsBuilder.fromHttpUrl(urlF + id + "/similar?")
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .toUriString();
        String url4 = UriComponentsBuilder.fromHttpUrl(urlF + id + "/videos?")
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .toUriString();
        System.out.println(url4);

        var dadosFilmes = restTemplate.getForObject(url, DadosMovies.class);
        var dadosAtores = restTemplate.getForObject(url2, CreditosDTO.class);
        var dadosFilmesRelacionados = restTemplate.getForObject(url3, MoviesTransfer.class);
        var dadosTraillerMovie = restTemplate.getForObject(url4, DadosTrailler.class);

        return converterDetalhesDTO(dadosFilmes, dadosAtores, dadosFilmesRelacionados, dadosTraillerMovie);
    }

    public List<MovieDTO> searchMoviesByGenre(String nome, int page) {
        var generoId = Genero.fromString(nome);
        System.out.println(generoId);
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/discover/movie")
                .queryParam("api_key", apiKey)
                .queryParam("page", page)
                .queryParam("with_genres", generoId)
                .queryParam("language", "pt-BR")
                .toUriString();
        var dados = restTemplate.getForObject(url, MoviesTransfer.class);
        return converterDTOList(dados);
    }

    public List<MovieDTO> searchMoviesByName(String nome, int page) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/movie?query=" + nome)
                .queryParam("page", page)
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .toUriString();
        System.out.println(url);
        MoviesTransfer dados = restTemplate.getForObject(url, MoviesTransfer.class);

        return converterDTOList(dados);
    }

    private List<MovieDTO> converterDTOList(MoviesTransfer dados) {
        return dados.results().stream().map(a -> new MovieDTO(new Movies(a)))
                .filter(img -> img.backdropPath() != null)
                .collect(Collectors.toList());
    }


    private DetalhesDTO converterDetalhesDTO(DadosMovies dados, CreditosDTO creditosDTO, MoviesTransfer relacionados,
                                             DadosTrailler trailler) {

        List<AtoresDTO> atores = (creditosDTO.cast() != null && !creditosDTO.cast().isEmpty()) ?
                creditosDTO.cast().stream()
                        .map(a -> new AtoresDTO(a.id(), a.name(), a.original_name(), a.profile_path(), a.character(), a.credit_id(), a.order()))
                        .toList() : Collections.emptyList();

        DiretorDTO diretor = (creditosDTO.crew() != null && !creditosDTO.crew().isEmpty()) ?
                creditosDTO.crew().stream()
                        .filter(z -> z.job().equalsIgnoreCase("Director"))
                        .findFirst()
                        .map(z -> new DiretorDTO(z.id(), z.name(), z.original_name(), z.profile_path(), z.credit_id(), z.job()))
                        .orElse(null) : null;

        List<TraillerDTO> TraillerDTO = (trailler.results() != null && !trailler.results().isEmpty()) ?
                trailler.results().stream()
                        .map(t -> new TraillerDTO(t.id(), t.name(), t.key()))
                        .toList() : Collections.emptyList();

        var semelhante = converterDTOList(relacionados);

        return new DetalhesDTO(dados, semelhante, atores, diretor, TraillerDTO);
    }


    public List<ResumeDetalhesDTO> getMoviesActor(String name,int page) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/person?=")
                .queryParam("query", name)
                .queryParam("page",page)
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .toUriString();
        var dados = restTemplate.getForObject(url, DadosMoviesActor.class);
        return dados != null && !dados.results().isEmpty() ?
                dados.results().stream()
                        .flatMap(moviesByActor -> moviesByActor.known_for().stream())
                        .filter(movie -> movie.title() != null && !movie.title().isBlank())
                        .filter(movie -> movie.poster_path() != null && !movie.poster_path().isBlank())
                        .map(ResumeDetalhesDTO::new)
                        .collect(Collectors.toList()) :
                List.of();
    }
}
