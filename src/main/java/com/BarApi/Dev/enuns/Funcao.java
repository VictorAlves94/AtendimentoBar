package com.BarApi.Dev.enuns;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Funcao  {
    ADMIN("ROLE_ADMIN"),
    GERENTE("ROLE_GERENTE"),
    GRACON("ROLE_GARCON"),
    BRALCONISTA("ROLE_BALCONISTA"),
    PORTARIA("ROLE_PORTARIA");
    private String descricao;

    Funcao(String descricao) {

        this.descricao = descricao;
    }
    public static Funcao toEnum(String descricao){
        if (descricao == null){
            return null;
        }
        for (Funcao x :Funcao.values()){
            if(descricao.equals(x.getDescricao())){
                return x;
            }
        }
        throw new IllegalArgumentException("Funcao Invalido");
    }
}
