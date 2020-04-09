package com.tonydpadua.categoria;

import com.tonydpadua.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
@AllArgsConstructor
@Service
public class CategoriaService {

    private CategoriaRepository repo;

    public Categoria  findById(Long id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException
                ("Objeto não encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()));
    }



}
