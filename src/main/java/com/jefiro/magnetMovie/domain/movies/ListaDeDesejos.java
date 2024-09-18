package com.jefiro.magnetMovie.domain.movies;

import com.jefiro.magnetMovie.domain.usuario.UsuarioDesejo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "usuario_desejo")
public class ListaDeDesejos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idInterno;
    private int nome;

    public ListaDeDesejos(UsuarioDesejo dados) {
        this.idInterno = dados.idUser();
        this.nome = dados.idMovie();
    }
}
