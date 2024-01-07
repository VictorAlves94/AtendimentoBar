package com.BarApi.Dev.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private Funcionario garcon;
    @OneToMany(mappedBy = "pedido")
    private List<Produto> produtos;
    private String mesa;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
