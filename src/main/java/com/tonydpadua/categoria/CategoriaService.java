package com.tonydpadua.categoria;

import com.tonydpadua.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Categoria save(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Long id,Categoria obj){
        try{
            Categoria cat1 = repo.getOne(id);
            updateData(cat1,obj);
            return repo.save(cat1);
        }
        catch (EntityNotFoundException e){
            throw new ObjectNotFoundException("Não foi encontrado ! Id: "+id);
        }
    }

    public void updateData(Categoria cat1,Categoria cat2){
        cat1.setNome(cat2.getNome());

    }

}
