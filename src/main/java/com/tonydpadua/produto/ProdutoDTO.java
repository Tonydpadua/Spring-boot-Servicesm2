package com.tonydpadua.produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String nome;

    private double preco;

    public ProdutoDTO(Produto produto){
        id=produto.getId();
        nome=produto.getNome();
        preco=produto.getPreco();
    }

}
