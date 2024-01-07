package com.BarApi.Dev.controllers;

import com.BarApi.Dev.dto.cliente.ClienteCadastrarDto;
import com.BarApi.Dev.dto.cliente.ClienteListarDto;
import com.BarApi.Dev.dto.funcionario.FuncionarioCadastrarDto;
import com.BarApi.Dev.dto.funcionario.FuncionarioListarDto;
import com.BarApi.Dev.services.ClienteServices;
import com.BarApi.Dev.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    @Autowired
    ClienteServices service;

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var usuario = service.buscarUsuario(id);
        return ResponseEntity.ok(usuario);

    }

    @GetMapping
    public ResponseEntity<Page<ClienteListarDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = service.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody ClienteCadastrarDto dados, UriComponentsBuilder uriBuilder) {
        var usuarioCadastrado = service.cadastrarCliente(dados);

        UriComponents uriComponents = uriBuilder.path("/funcionarios/{id}").buildAndExpand(usuarioCadastrado.getId());
        URI uri = uriComponents.toUri();


        return ResponseEntity.created(uri).body(usuarioCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody ClienteCadastrarDto dados, @PathVariable("id") Long id) {
        var usuarioAtt = service.atualizarUsuario(dados, id);


        return ResponseEntity.ok(usuarioAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        service.excluirCliente(id);
        return ResponseEntity.ok().build();
    }
}
