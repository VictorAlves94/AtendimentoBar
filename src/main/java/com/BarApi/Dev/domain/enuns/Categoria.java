package com.BarApi.Dev.domain.enuns;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public enum Categoria {
    BEBIDAS_ALCOOLICAS(0),
    BEBIDAS_NAO_ALCOOLICAS(1),
    PETISCOS(2),
    DRINKS(3),
    COMBOS(4);
    private Integer codigo;

    Categoria(Integer codigo) {
        this.codigo = codigo;
    }
    public static Categoria toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Categoria x :Categoria.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Categoria Invalido");
    }
}
