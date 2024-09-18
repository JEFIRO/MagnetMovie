package com.jefiro.magnetMovie.controller;

import com.jefiro.magnetMovie.domain.infra.security.DadosAtenticacao;
import com.jefiro.magnetMovie.domain.infra.security.TokenDTO;
import com.jefiro.magnetMovie.domain.service.TokenService;
import com.jefiro.magnetMovie.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> getToken(@RequestBody DadosAtenticacao dados) {
        try {
            var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var auth = manager.authenticate(token);
            var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new TokenDTO(tokenJWT, ((Usuario) auth.getPrincipal()).getIdInterno()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
