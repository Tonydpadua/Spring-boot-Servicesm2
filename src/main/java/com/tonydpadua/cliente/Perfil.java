package com.tonydpadua.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Perfil {
    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(3,"ROLE_CLIENTE");

    private int cod;
    private String descricao;

    public static Perfil toEnum(Integer cod){
        if(cod==null){
            return null;
        }
        for(Perfil c : Perfil.values()){
            if(cod.equals(c.getCod())){
                return c;
            }
        }
        throw new IllegalArgumentException("Id inválido: "+cod);
    }

}
