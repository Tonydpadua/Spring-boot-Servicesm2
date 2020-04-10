package com.tonydpadua.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoPagamento {
    PENDENTE(1,"Pendente"),
    QUITADO(2,"Quitado"),
    CANCELADO(3,"Cancelado");

    private int cod;
    private String descricao;

    public static EstadoPagamento toEnum(Integer cod){
        if(cod==null){
            return null;
        }
        for(EstadoPagamento c : EstadoPagamento.values()){
            if(cod.equals(c.getCod())){
                return c;
            }
        }
        throw new IllegalArgumentException("Id inválido: "+cod);
    }

}
