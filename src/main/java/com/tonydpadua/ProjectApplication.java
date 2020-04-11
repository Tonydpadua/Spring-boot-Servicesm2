package com.tonydpadua;

import com.tonydpadua.categoria.Categoria;
import com.tonydpadua.categoria.CategoriaRepository;
import com.tonydpadua.cidade.Cidade;
import com.tonydpadua.cidade.CidadeRepository;
import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.cliente.ClienteRepository;
import com.tonydpadua.cliente.TipoCliente;
import com.tonydpadua.endereco.Endereco;
import com.tonydpadua.endereco.EnderecoRepository;
import com.tonydpadua.estado.Estado;
import com.tonydpadua.estado.EstadoRepository;
import com.tonydpadua.itempedido.ItemPedido;
import com.tonydpadua.itempedido.ItemPedidoRepository;
import com.tonydpadua.pagamento.*;
import com.tonydpadua.pedido.Pedido;
import com.tonydpadua.pedido.PedidoRepository;
import com.tonydpadua.produto.Produto;
import com.tonydpadua.produto.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    private CategoriaRepository categoriaRepository;

    private ProdutoRepository produtoRepository;

    private EstadoRepository estadoRepository;

    private CidadeRepository cidadeRepository;

    private ClienteRepository clienteRepository;

    private EnderecoRepository enderecoRepository;

    private PedidoRepository pedidoRepository;

    private PagamentoRepository pagamentoRepository;

    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(null,"Informática");
        Categoria c2 = new Categoria(null,"Escritório");

        Produto p1 = new Produto(null,"Computador",2000);
        Produto p2 = new Produto(null,"Cadeira",800);
        Produto p3 = new Produto(null,"Impressora",2000);

        c1.getProdutos().addAll((Arrays.asList(p1,p2,p3)));
        c2.getProdutos().add(p2);

        p1.getCategorias().add(c1);
        p2.getCategorias().addAll(Arrays.asList(c1,c2));
        p3.getCategorias().add(c1);

        categoriaRepository.saveAll(Arrays.asList(c1,c2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));


        Estado e1 = new Estado(null,"Minas Gerais");
        Estado e2 = new Estado(null,"Paraíba");

        Cidade ci1 = new Cidade(null,"Belo Horizonte",e1);
        Cidade ci2 = new Cidade(null,"João Pessoa",e2);
        Cidade ci3 = new Cidade(null,"Patos",e2);

        e1.getCidades().add(ci1);
        e2.getCidades().addAll(Arrays.asList(ci2,ci3));

        estadoRepository.saveAll(Arrays.asList(e1,e2));
        cidadeRepository.saveAll(Arrays.asList(ci1,ci2,ci3));


        Cliente cl1 = new Cliente(null,"Maria","maria@gmail.com","5345696", TipoCliente.PESSOAFISICA);
        cl1.getTelefones().addAll(Arrays.asList("34234235","233245"));

        Endereco en1 = new Endereco(null,"Avenida Beira Rio","300","Apto 200","Jardim","23454857",cl1,ci2);
        Endereco en2 = new Endereco(null,"Avenida de Patos","300","Apto 200","Jardim","23454857",cl1,ci3);

        cl1.getEnderecos().addAll(Arrays.asList(en1,en2));

        clienteRepository.save(cl1);
        enderecoRepository.saveAll(Arrays.asList(en1,en2));


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cl1,en1);
        Pedido ped2 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cl1,en2);

        Pagamento pa1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
        ped1.setPagamento(pa1);

        Pagamento pa2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 10:30"),null);
        ped2.setPagamento(pa2);

        cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pa1,pa2));


        ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
        ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
        ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));


    }
}
