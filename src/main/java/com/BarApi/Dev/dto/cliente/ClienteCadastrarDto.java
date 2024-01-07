package com.BarApi.Dev.dto.cliente;

import com.BarApi.Dev.domain.Pedidos;
import com.BarApi.Dev.enuns.Funcao;

import java.time.LocalDate;
import java.util.List;


public record ClienteCadastrarDto(
        Long id,
        String nome,
        Integer codigo,
        LocalDate horaCriacao,
        LocalDate   horaFinalizando,
        List<Pedidos> pedidos,
        List<Pedidos> contaDetalhada,
        Double contaTotal
        ){

}
