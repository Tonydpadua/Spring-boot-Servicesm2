package com.tonydpadua.produto;

import com.tonydpadua.produto.url.URL;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoRestController {

    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome",defaultValue = "")String nome,
            @RequestParam(value = "categorias",defaultValue = "")String categorias,
            @RequestParam(value = "page",defaultValue = "0")Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")String direction){
        String nomeDecoded = URL.decodeParam(nome);
        List<Long> ids= URL.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded,ids,page,linesPerPage,orderBy,direction);
        Page<ProdutoDTO> listDTO = list.map(obj->new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }


}
