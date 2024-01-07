package com.BarApi.Dev.domain;

import com.BarApi.Dev.enuns.CategoriaEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nome;
private String descricao;
private Float valor;
private CategoriaEnum categoriaEnum;
private Integer quantEstoque = 0;
@ManyToOne
@JoinColumn(name = "pedido_id")
private Pedidos pedido;
}
