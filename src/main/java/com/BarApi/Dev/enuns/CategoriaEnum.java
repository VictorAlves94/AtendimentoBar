package com.BarApi.Dev.enuns;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum CategoriaEnum {
    BEBIDAS_ALCOOLICAS("Bebida Alcoolica"),
    BEBIDAS_NAO_ALCOOLICAS("Bebida Nao Alcoolica"),
    PETISCOS("Petisco"),
    DRINKS("Drinks"),
    COMBOS("Combos");

    private String categorias;

    CategoriaEnum(String nome) {
        this.categorias = nome;
    }
    public static CategoriaEnum toEnum(String nome){
        if (nome == null){
            return null;
        }
        for (CategoriaEnum x : CategoriaEnum.values()){
            if(nome.equals(x.getCategorias())){
                return x;
            }
        }
        throw new IllegalArgumentException("Categoria Invalido");
    }
}
