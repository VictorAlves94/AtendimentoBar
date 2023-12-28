package com.BarApi.Dev.domain;

import com.BarApi.Dev.domain.enuns.Funcao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    @Column(unique = true)
    private String email;
    private String senha;
    @Embedded
    Funcao funcao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();


    public Funcionario(Long id, String nome, String sobrenome, String cpf, String telefone, String email, String senha, Funcao funcao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.funcao = funcao;
        this.dataCriacao = LocalDate.now();
    }
}






