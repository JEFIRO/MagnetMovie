package com.jefiro.magnetMovie.controller;

import com.jefiro.magnetMovie.domain.repository.DesejoRepository;
import com.jefiro.magnetMovie.domain.usuario.RespostaCadastro;
import com.jefiro.magnetMovie.domain.usuario.Usuario;
import com.jefiro.magnetMovie.domain.repository.UsuarioRepository;
import com.jefiro.magnetMovie.domain.usuario.UsuarioCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private DesejoRepository desejoRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody UsuarioCadastro dados) {
        var newUser = new Usuario(dados);
        repository.save(newUser);
        return ResponseEntity.ok(new RespostaCadastro("Usuário cadastrado com sucesso"));
    }

}
