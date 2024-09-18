package com.jefiro.magnetMovie.controller;

import com.jefiro.magnetMovie.domain.movies.dto.DetalhesDTO;
import com.jefiro.magnetMovie.domain.movies.dto.MovieDTO;
import com.jefiro.magnetMovie.domain.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class TmdbController {

    @Autowired
    private TmdbService service;

    @GetMapping
    public ResponseEntity  getPopularMovies(@RequestParam int page) {
        return ResponseEntity.ok(service.getPopularMovies(page));
    }

    @GetMapping("/details")
    public ResponseEntity getDetalhes(@RequestParam int id) {
        return ResponseEntity.ok(service.getDetalhes(id));
    }

    @GetMapping("/procura")
    public ResponseEntity searchMovieByName(@RequestParam String name, @RequestParam int page) {
        return ResponseEntity.ok(service.searchMoviesByName(name, page));
    }

    @GetMapping("/gender")
    public ResponseEntity searchMovieByGenre(@RequestParam String name, @RequestParam int page) {
        return ResponseEntity.ok(service.searchMoviesByGenre(name, page));
    }

    @GetMapping("/ator")
    public ResponseEntity searchMovieByActor(@RequestParam String name, @RequestParam int page) {
        return ResponseEntity.ok(service.getMoviesActor(name, page));
    }

}
