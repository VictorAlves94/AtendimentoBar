package com.BarApi.Dev.domain.dto;

import com.BarApi.Dev.domain.enuns.Funcao;

import java.util.Set;

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
