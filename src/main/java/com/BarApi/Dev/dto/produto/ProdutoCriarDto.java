package com.BarApi.Dev.dto.produto;

import com.BarApi.Dev.enuns.CategoriaEnum;

public record ProdutoCriarDto(Long id,
                              String nome,
                              String descricao,
                              Float valor,
                              CategoriaEnum categoriaEnum,
                              Integer quantEstoque
) {
}
