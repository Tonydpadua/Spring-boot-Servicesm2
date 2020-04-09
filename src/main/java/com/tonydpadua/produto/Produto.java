package com.tonydpadua.produto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tonydpadua.categoria.Categoria;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "produto_categoria",joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    
    public Produto(Long id,String nome,double preco) {
        this.id=id;
        this.nome=nome;
        this.preco=preco;
    }


}
