package com.BarApi.Dev.domain.dto;

import com.BarApi.Dev.domain.Funcionario;
import com.BarApi.Dev.domain.enuns.Funcao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record FuncionarioListarDto(Long id,
                                   String nome,
                                   String sobrenome,
                                   String cpf,
                                   String telefone,
                                   String email,
                                   String senha,
                                   Funcao funcao,
                                   LocalDate dataCriacao)

{



}

