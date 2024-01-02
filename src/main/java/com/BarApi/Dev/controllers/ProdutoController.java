package com.BarApi.Dev.controllers;

import com.BarApi.Dev.dto.produto.ProdutoCriarDto;
import com.BarApi.Dev.dto.produto.ProdutoListarDto;
import com.BarApi.Dev.services.ProdutoServices;
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
@RequestMapping(value = "/produtos")
public class ProdutoController {
    @Autowired
    ProdutoServices service;

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var produto = service.buscarProduto(id);
        return ResponseEntity.ok(produto);

    }

    @GetMapping
    public ResponseEntity<Page<ProdutoListarDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = service.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody ProdutoCriarDto dados, UriComponentsBuilder uriBuilder) {
        var produtoCadastrado = service.cadastrarProdutos(dados);

        UriComponents uriComponents = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoCadastrado.getId());
        URI uri = uriComponents.toUri();


        return ResponseEntity.created(uri).body(produtoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody ProdutoCriarDto dados, @PathVariable("id") Long id) {
        var produtoAtt = service.atualizarUsuario(dados, id);


        return ResponseEntity.ok(produtoAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable("id") Long id){
        service.deletarProduto(id);
        return ResponseEntity.ok().build();
    }
}
