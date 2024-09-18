package com.jefiro.magnetMovie.controller;

import com.jefiro.magnetMovie.domain.service.DesejoService;
import com.jefiro.magnetMovie.domain.usuario.UsuarioDesejo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/listadesejo")
public class DesejoController {
    @Autowired
    private DesejoService service;

    @PostMapping()
    public ResponseEntity listaDesejo(@RequestBody UsuarioDesejo dados) {
        System.out.println("hi");
        var resposta = service.salvarFilme(dados);
        System.out.println(resposta);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/sher")
    public ResponseEntity listaDesejoGet(@RequestBody UsuarioDesejo idUser) {
        System.out.println(idUser);
        var movies = service.getFilmesPeloUsuario(idUser.idUser());
        System.out.println(movies);
        return ResponseEntity.ok(movies);
    }

}















