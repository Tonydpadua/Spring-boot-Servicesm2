package com.tonydpadua.pedido;

import com.tonydpadua.cliente.ClienteService;
import com.tonydpadua.exceptions.ObjectNotFoundException;
import com.tonydpadua.itempedido.ItemPedido;
import com.tonydpadua.itempedido.ItemPedidoRepository;
import com.tonydpadua.pagamento.EstadoPagamento;
import com.tonydpadua.pagamento.PagamentoComBoleto;
import com.tonydpadua.pagamento.PagamentoRepository;
import com.tonydpadua.pedido.boleto.BoletoService;
import com.tonydpadua.produto.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    private BoletoService boletoService;

    private PagamentoRepository pagamentoRepository;

    private ProdutoService produtoService;

    private ItemPedidoRepository itemPedidoRepository;

    private ClienteService clienteService;

    public Pedido findById(Long id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Object não encontrado! Id: "+id));
    }

    @Transactional
    public Pedido save(Pedido obj){
        obj.setId(null);
        obj.setInstance(new Date());
        obj.setCliente(clienteService.findById(obj.getCliente().getId()));
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto,obj.getInstance());
        }
        obj=pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        System.out.println(obj);
        return obj;

    }

}
