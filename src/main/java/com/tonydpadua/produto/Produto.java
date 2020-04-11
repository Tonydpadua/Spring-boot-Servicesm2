package com.tonydpadua.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tonydpadua.categoria.Categoria;
import com.tonydpadua.itempedido.ItemPedido;
import com.tonydpadua.pedido.Pedido;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private double preco;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "produto_categoria",joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();
    
    public Produto(Long id,String nome,double preco) {
        this.id=id;
        this.nome=nome;
        this.preco=preco;
    }

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> lista = new ArrayList<>();
        for(ItemPedido c : itens){
            lista.add(c.getPedido());
        }
        return lista;
    }


}
