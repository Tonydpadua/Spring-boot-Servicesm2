package com.tonydpadua.pagamento;

import com.tonydpadua.pedido.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID=1L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
