package com.tonydpadua.pedido;

import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.endereco.Endereco;
import com.tonydpadua.pagamento.Pagamento;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date instance;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;


    public Pedido(Long id, Date instance, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.instance = instance;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Pedido() {
    }
}
