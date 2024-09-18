package com.jefiro.magnetMovie.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String senha;
    private String idInterno;
    private boolean ativo;

    public Usuario(DadoUsuario dado) {
        this.nome = dado.nome();
        this.email = dado.Email();
        this.dataNascimento = dado.dataNascimento();
        this.senha = dado.senha();
        this.userName = dado.userName();
    }

    public Usuario(UsuarioCadastro dados) {
        this.nome = dados.name();
        this.email = dados.email();
        this.dataNascimento = dados.date();
        this.senha = cripitografarSenha(dados.password());
        this.userName = dados.userName();
        this.idInterno = geraIdInterno();
        this.ativo = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    private String cripitografarSenha(String senha){
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
    private String geraIdInterno() {
        var randon = new Random().nextInt(100000);
        return String.format(userName + "#" + "%06d", randon);
    }
}
