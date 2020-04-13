package com.tonydpadua.cliente;

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
public class ClienteService {

    private ClienteRepository repo;

    public Cliente findById(Long id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Object não encontrado! Id: "+id));
    }

    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public void deleteById(Long id){
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
        }
    }

    public Cliente update(Long id, Cliente obj){

        try{
            Cliente cat1 = repo.getOne(id);
            updateData(cat1,obj);
            return repo.save(cat1);
        }
        catch (EntityNotFoundException e){
            throw new ObjectNotFoundException("Não foi encontrado ! Id: "+id);
        }


    }

    public void updateData(Cliente cat1, Cliente cat2){
        cat1.setNome(cat2.getNome());
        cat1.setEmail(cat2.getEmail());

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);

    }


}
