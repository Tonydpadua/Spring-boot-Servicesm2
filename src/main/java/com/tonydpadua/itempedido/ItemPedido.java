package com.tonydpadua.itempedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tonydpadua.pedido.Pedido;
import com.tonydpadua.produto.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class ItemPedido implements Serializable {

    private static final long serialVersionUID=1L;

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private double desconto;
    private Integer quantidade;
    private double preco;

    public ItemPedido(Pedido pedido, Produto produto,double desconto, Integer quantidade, double preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedidoPK getId(){
        return id;
    }

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }

    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    public double getSubTotal(){
        return (preco-desconto)*quantidade;
    }
}
