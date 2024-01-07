package com.BarApi.Dev.controllers;

import com.BarApi.Dev.dto.pedidos.PedidosCriarDto;
import com.BarApi.Dev.dto.pedidos.PedidosListarDto;
import com.BarApi.Dev.dto.produto.ProdutoCriarDto;
import com.BarApi.Dev.dto.produto.ProdutoListarDto;
import com.BarApi.Dev.services.PedidosService;
import com.BarApi.Dev.services.ProdutoServices;
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
@RequestMapping(value = "/pedidos")
public class PedidosController {
    @Autowired
    PedidosService service;

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var pedido = service.buscarPedido(id);
        return ResponseEntity.ok(pedido);

    }

    @GetMapping
    public ResponseEntity<Page<PedidosListarDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = service.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody PedidosCriarDto dados, UriComponentsBuilder uriBuilder) {
        var pedidoCadastrado = service.cadastrarPedido(dados);

        UriComponents uriComponents = uriBuilder.path("/produtos/{id}").buildAndExpand(pedidoCadastrado.getId());
        URI uri = uriComponents.toUri();


        return ResponseEntity.created(uri).body(pedidoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody PedidosCriarDto dados, @PathVariable("id") Long id) {
        var produtoAtt = service.atualizarPedido(dados, id);


        return ResponseEntity.ok(produtoAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable("id") Long id){
        service.deletarPedido(id);
        return ResponseEntity.ok().build();
    }
}
