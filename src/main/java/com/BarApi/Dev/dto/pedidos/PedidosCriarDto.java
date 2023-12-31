package com.BarApi.Dev.dto.pedidos;

import com.BarApi.Dev.domain.Cliente;
import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.Produto;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public record PedidosCriarDto(
                              Long id,
                              Long contaId,
                              @NotNull
                              String nomeGarcon,
                              List<Produto>produtos,
                              String mesa) {
}
