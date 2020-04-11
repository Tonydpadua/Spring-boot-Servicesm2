package com.tonydpadua.estado;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tonydpadua.cidade.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
public class Estado implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<>();

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
