package com.BarApi.Dev.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "conta")
    private List<Pedidos> pedidos = new ArrayList<>();

    private BigDecimal valorTotal = BigDecimal.ZERO;

    public void adicionarPedido(Pedidos pedido) {
        this.pedidos.add(pedido);
        this.valorTotal = this.valorTotal.add(pedido.calcularValorTotal());
    }
}
