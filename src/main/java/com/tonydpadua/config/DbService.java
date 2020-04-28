package com.tonydpadua.config;

import com.tonydpadua.categoria.Categoria;
import com.tonydpadua.categoria.CategoriaRepository;
import com.tonydpadua.cidade.Cidade;
import com.tonydpadua.cidade.CidadeRepository;
import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.cliente.ClienteRepository;
import com.tonydpadua.cliente.Perfil;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
@AllArgsConstructor
@Service
public class DbService {
    private CategoriaRepository categoriaRepository;

    private ProdutoRepository produtoRepository;

    private EstadoRepository estadoRepository;

    private CidadeRepository cidadeRepository;

    private ClienteRepository clienteRepository;

    private EnderecoRepository enderecoRepository;

    private PedidoRepository pedidoRepository;

    private PagamentoRepository pagamentoRepository;

    private ItemPedidoRepository itemPedidoRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() throws ParseException {

        Categoria c1 = new Categoria(null,"Informática");
        Categoria c2 = new Categoria(null,"Escritório");
        Categoria c3 = new Categoria(null,"Cama mesa e banho");
        Categoria c4 = new Categoria(null,"Eletrônicos");
        Categoria c5 = new Categoria(null,"Jardinagem");
        Categoria c6 = new Categoria(null,"Decoração");
        Categoria c7 = new Categoria(null,"Perfumaria");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto p5 = new Produto(null, "Toalha", 50.00);
        Produto p6 = new Produto(null, "Colcha", 200.00);
        Produto p7 = new Produto(null, "TV true color", 1200.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Shampoo", 90.00);


        c1.getProdutos().addAll((Arrays.asList(p1,p2,p3)));
        c2.getProdutos().addAll((Arrays.asList(p2,p4)));
        c3.getProdutos().addAll(Arrays.asList(p5, p6));
        c4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        c5.getProdutos().addAll(Arrays.asList(p8));
        c6.getProdutos().addAll(Arrays.asList(p9, p10));
        c7.getProdutos().addAll(Arrays.asList(p11));


        p1.getCategorias().add(c1);
        p2.getCategorias().addAll(Arrays.asList(c1,c2,c4));
        p3.getCategorias().add(c1);
        p4.getCategorias().addAll(Arrays.asList(c2));
        p5.getCategorias().addAll(Arrays.asList(c3));
        p6.getCategorias().addAll(Arrays.asList(c3));
        p7.getCategorias().addAll(Arrays.asList(c4));
        p8.getCategorias().addAll(Arrays.asList(c5));
        p9.getCategorias().addAll(Arrays.asList(c6));
        p10.getCategorias().addAll(Arrays.asList(c6));
        p11.getCategorias().addAll(Arrays.asList(c7));

        categoriaRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));


        Estado e1 = new Estado(null,"Minas Gerais");
        Estado e2 = new Estado(null,"Paraíba");

        Cidade ci1 = new Cidade(null,"Belo Horizonte",e1);
        Cidade ci2 = new Cidade(null,"João Pessoa",e2);
        Cidade ci3 = new Cidade(null,"Patos",e2);

        e1.getCidades().add(ci1);
        e2.getCidades().addAll(Arrays.asList(ci2,ci3));

        estadoRepository.saveAll(Arrays.asList(e1,e2));
        cidadeRepository.saveAll(Arrays.asList(ci1,ci2,ci3));


        Cliente cl1 = new Cliente(null,"Maria","maria@gmail.com","93174388490", TipoCliente.PESSOAFISICA,bCryptPasswordEncoder.encode("123"));
        cl1.getTelefones().addAll(Arrays.asList("34234235","233245"));

        Cliente cl2 = new Cliente(null,"Ana","ana@gmail.com","56772740548", TipoCliente.PESSOAFISICA,bCryptPasswordEncoder.encode("123"));
        cl2.getTelefones().addAll(Arrays.asList("54641684","5414841"));
        cl2.addPerfil(Perfil.ADMIN);

        Endereco en1 = new Endereco(null,"Avenida Beira Rio","300","Apto 200","Jardim","23454857",cl1,ci2);
        Endereco en2 = new Endereco(null,"Avenida de Patos","300","Apto 200","Jardim","23454857",cl1,ci3);
        Endereco en3 = new Endereco(null,"Avenida Epitacio Pessoa","300","Apto 200","Miramar","23454857",cl2,ci2);

        cl1.getEnderecos().addAll(Arrays.asList(en1,en2));
        cl2.getEnderecos().addAll(Arrays.asList(en3));

        clienteRepository.saveAll(Arrays.asList(cl1,cl2));
        enderecoRepository.saveAll(Arrays.asList(en1,en2,en3));


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
