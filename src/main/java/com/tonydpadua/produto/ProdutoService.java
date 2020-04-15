package com.tonydpadua.produto;

import com.tonydpadua.categoria.Categoria;
import com.tonydpadua.categoria.CategoriaRepository;
import com.tonydpadua.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    private CategoriaRepository categoriaRepository;

    public Produto findById(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Object não encontrado! Id: "+id));
    }

    public Page<Produto> search(String nome, List<Long> ids,Integer page,Integer linesPerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categoria> categorias  = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);

    }

}
