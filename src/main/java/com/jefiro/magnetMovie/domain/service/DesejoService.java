package com.jefiro.magnetMovie.domain.service;

import com.jefiro.magnetMovie.domain.movies.ListaDeDesejos;
import com.jefiro.magnetMovie.domain.movies.dto.DetalhesDTO;
import com.jefiro.magnetMovie.domain.movies.dto.MoviesDetalhesDTO;
import com.jefiro.magnetMovie.domain.repository.DesejoRepository;
import com.jefiro.magnetMovie.domain.service.TmdbService;
import com.jefiro.magnetMovie.domain.usuario.UsuarioDesejo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesejoService {
    @Autowired
    private DesejoRepository desejoRepository;
    @Autowired
    private TmdbService service;

    public String salvarFilme(UsuarioDesejo dados) {
        var desejo = new ListaDeDesejos(dados);
        desejoRepository.save(desejo);
        return "Salvo com sucesso";
    }

    public List<DetalhesDTO> getFilmesPeloUsuario(String idUser) {
        var lista = desejoRepository.findByIdInterno(idUser);
        System.out.println(lista);
        var filmes = converteDTO(lista);
        System.out.println(filmes);
        return filmes;
    }

    private List<DetalhesDTO> converteDTO(List<ListaDeDesejos> dados) {
        return dados.stream().map(a -> service.getDetalhes(a.getNome())).toList();
    }

}
