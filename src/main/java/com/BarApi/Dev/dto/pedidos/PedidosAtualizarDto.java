package com.BarApi.Dev.dto.pedidos;

import com.BarApi.Dev.domain.Produto;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public record PedidosAtualizarDto(
                              @NotNull
                              String nomeGarcon,
                              List<Produto>produtos,
                              String mesa) {
}
