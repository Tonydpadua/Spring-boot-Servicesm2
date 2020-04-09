package com.tonydpadua;

import com.tonydpadua.categoria.Categoria;
import com.tonydpadua.categoria.CategoriaRepository;
import com.tonydpadua.cidade.Cidade;
import com.tonydpadua.cidade.CidadeRepository;
import com.tonydpadua.estado.Estado;
import com.tonydpadua.estado.EstadoRepository;
import com.tonydpadua.produto.Produto;
import com.tonydpadua.produto.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    private CategoriaRepository categoriaRepository;

    private ProdutoRepository produtoRepository;

    private EstadoRepository estadoRepository;

    private CidadeRepository cidadeRepository;

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




    }
}
