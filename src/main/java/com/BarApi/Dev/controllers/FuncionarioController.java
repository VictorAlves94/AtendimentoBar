package com.BarApi.Dev.controllers;

import com.BarApi.Dev.dto.funcionario.FuncionarioCadastrarDto;
import com.BarApi.Dev.dto.funcionario.FuncionarioListarDto;
import com.BarApi.Dev.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    @Autowired
    FuncionarioServices service;

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var usuario = service.buscarUsuario(id);
        return ResponseEntity.ok(usuario);

    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioListarDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = service.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody FuncionarioCadastrarDto dados, UriComponentsBuilder uriBuilder) {
        var usuarioCadastrado = service.cadastrarFuncionario(dados);

        UriComponents uriComponents = uriBuilder.path("/funcionarios/{id}").buildAndExpand(usuarioCadastrado.getId());
        URI uri = uriComponents.toUri();


        return ResponseEntity.created(uri).body(usuarioCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody FuncionarioCadastrarDto dados, @PathVariable("id") Long id) {
        var usuarioAtt = service.atualizarUsuario(dados, id);


        return ResponseEntity.ok(usuarioAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desligar(@PathVariable("id") Long id){
        service.demitirFuncionario(id);
        return ResponseEntity.ok().build();
    }
}
