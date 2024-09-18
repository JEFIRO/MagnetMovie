package com.jefiro.magnetMovie.domain.repository;

import com.jefiro.magnetMovie.domain.movies.ListaDeDesejos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesejoRepository extends JpaRepository<ListaDeDesejos,Long> {
    List<ListaDeDesejos> findByIdInterno(String id);
}
