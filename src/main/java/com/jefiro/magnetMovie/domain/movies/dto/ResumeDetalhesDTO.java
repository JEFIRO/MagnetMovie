package com.jefiro.magnetMovie.domain.movies.dto;

public record ResumeDetalhesDTO(
        int id,
        String title,
        String poster_path,
        double vote_average)
{
    public ResumeDetalhesDTO(ResumeDetalhes dados) {
        this(dados.id(), dados.title(), dados.poster_path(), dados.vote_average());
    }
}
