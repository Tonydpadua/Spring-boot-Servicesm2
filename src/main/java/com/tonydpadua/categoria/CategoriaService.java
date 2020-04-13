package com.tonydpadua.categoria;

import com.tonydpadua.exceptions.DataIntegrityException;
import com.tonydpadua.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoriaService {

    private CategoriaRepository repo;

    public List<Categoria> findAll(){
        return repo.findAll();
    }

    public Categoria  findById(Long id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException
                ("Objeto não encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()));
    }

    public Categoria save(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public void deleteById(Long id){
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos");
        }
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

    public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(),objDTO.getNome());
    }

}
