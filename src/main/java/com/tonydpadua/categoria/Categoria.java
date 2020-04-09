package com.tonydpadua.categoria;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tonydpadua.produto.Produto;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID=1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonManagedReference
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();


    public Categoria(Long id,String nome) {
        this.id=id ;
        this.nome=nome;
    }


}
