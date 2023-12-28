package com.BarApi.Dev.domain.dto;

import com.BarApi.Dev.domain.enuns.Funcao;


public record FuncionarioCadastrarDto(

        String nome,
        String sobrenome,
        String cpf,
        String telefone,
        String email,
        String senha,
        Funcao funcao
        ){

}
