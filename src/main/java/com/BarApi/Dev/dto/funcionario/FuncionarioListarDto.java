package com.BarApi.Dev.dto.funcionario;

import com.BarApi.Dev.enuns.Funcao;

import java.time.LocalDate;

public record FuncionarioListarDto(Long id,
                                   String nome,
                                   String sobrenome,
                                   String cpf,
                                   String telefone,
                                   String email,
                                   String senha,
                                   Funcao funcao,
                                   LocalDate dataAdmissao,
                                   LocalDate dataDemissao,
                                   Boolean ativo)
{



}

