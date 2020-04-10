package com.tonydpadua.pagamento;

import com.tonydpadua.pedido.Pedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID=1L;
    @Id

    private Long id;
    private Integer estadoPagamento;
    @OneToOne
    @JoinColumn(name="pedido_id")
    @MapsId
    private Pedido pedido;


    public Pagamento(Long id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = estadoPagamento.getCod();
        this.pedido = pedido;
    }

    public EstadoPagamento getEstadoPagamento(){
        return EstadoPagamento.toEnum(estadoPagamento);
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento){
        this.estadoPagamento=estadoPagamento.getCod();
    }

}
