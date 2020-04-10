package com.tonydpadua.pagamento;

import com.tonydpadua.pedido.Pedido;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;
@Getter
@Setter
@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID=1L;

    private Date dataVencimento;
    private Date dataPagamento;




    public PagamentoComBoleto(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento,Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento=dataVencimento;
        this.dataPagamento=dataPagamento;
    }

    public PagamentoComBoleto() {
    }
}
