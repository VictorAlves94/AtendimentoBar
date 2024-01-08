package com.BarApi.Dev.dto.cliente;

import com.BarApi.Dev.domain.Conta;
import com.BarApi.Dev.domain.Pedidos;
import com.BarApi.Dev.enuns.Funcao;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;


public record ClienteCadastrarDto(
        Long id,
        String nome,
        @NotNull
        Integer codigo,
        Conta conta


        ){

}
