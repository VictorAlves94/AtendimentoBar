package com.BarApi.Dev.domain;

import com.BarApi.Dev.domain.enuns.Categoria;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Repository;

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
@Embedded
private Categoria categoria;
private int quantEstoque;

}
