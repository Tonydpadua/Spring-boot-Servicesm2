package com.tonydpadua.categoria;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaRestController {

    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


}
