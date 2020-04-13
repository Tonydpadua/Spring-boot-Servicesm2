package com.tonydpadua.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tonydpadua.endereco.Endereco;
import com.tonydpadua.pedido.Pedido;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String cpfOuCnpj;

    private Integer tipo;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos= new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo =(tipo==null)?null: tipo.getCod();
    }

    public TipoCliente getTipo(){
        return TipoCliente.toEnum(tipo);
    }
}
