package com.BarApi.Dev.domain.enuns;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public enum Funcao  {
    ADMIN(0, "ROLE_ADMIN"),
    GERENTE(1, "ROLE_GERENTE"),
    GRACON(2, "ROLE_GARCON"),
    BRALCONISTA(3, "ROLE_BALCONISTA"),
    PORTARIA(4, "ROLE_PORTARIA");
    private Integer codigo;
    private String descricao;

    Funcao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    public static Funcao toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Funcao x :Funcao.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Funcao Invalido");
    }
}
