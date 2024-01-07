package com.BarApi.Dev.dto.pedidos;

import com.BarApi.Dev.domain.Cliente;
import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.Produto;

import java.util.List;

public record PedidosCriarDto(
                              Long id,
                              Cliente cliente,
                              Funcionario garcon,
                              List<Produto>produtos,
                              String mesa) {
}
