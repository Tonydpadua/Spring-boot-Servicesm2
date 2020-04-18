package com.tonydpadua.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.endereco.Endereco;
import com.tonydpadua.itempedido.ItemPedido;
import com.tonydpadua.pagamento.Pagamento;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instance;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;


    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();


    public Pedido(Long id, Date instance, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.instance = instance;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Pedido() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public double getValorTotal(){
        double soma=0.0;
        for(ItemPedido ip : itens){
            soma+=ip.getSubTotal();
        }
        return soma;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Número do pedido: ");
        sb.append(getId());
        sb.append(", Instante: ");
        sb.append(sdf.format(getInstance()));
        sb.append(", Cliente: ");
        sb.append(getCliente().getNome());
        sb.append(", Situação do pagamento: ");
        sb.append(getPagamento().getEstadoPagamento().getDescricao());
        sb.append("\nDetalhes:  ");
        for(ItemPedido i : getItens()){
            sb.append(i.toString());
        }
        sb.append("Valor total: ");
        sb.append(nf.format(getValorTotal()));
        return sb.toString();
    }
}
