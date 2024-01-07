package com.BarApi.Dev.dto.funcionario;

import com.BarApi.Dev.enuns.Funcao;


public record FuncionarioCadastrarDto(
        Long id,

        String nome,
        String sobrenome,
        String cpf,
        String telefone,
        String email,
        String senha,
        Funcao funcao
        ){

}
