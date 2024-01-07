package com.BarApi.Dev.dto.pedidos;

import com.BarApi.Dev.domain.Cliente;
import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.Produto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public record PedidosListarDto(
       Long id,
       Cliente cliente,
       Funcionario garcon,
       List<Produto>produtos,
       String mesa
) {
}
