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
import java.util.stream.Collectors;

@Data
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
    @JsonIgnore
    private String senha;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Endereco> enderecos= new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(){
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipo,String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo =(tipo==null)?null: tipo.getCod();
        this.senha=senha;
        addPerfil(Perfil.CLIENTE);
    }

    public TipoCliente getTipo(){
        return TipoCliente.toEnum(tipo);
    }

    public Set<Perfil> getPerfil(){
        return perfis.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }
}

