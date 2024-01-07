package com.BarApi.Dev.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer codigo;
    private LocalDateTime horaCriacao = LocalDateTime.now();
    private LocalDateTime horaFinalizando = null;
    @OneToOne(mappedBy = "cliente")
    private Conta conta;
    private Double contaTotal;

}
