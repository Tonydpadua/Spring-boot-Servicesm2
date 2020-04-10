package com.tonydpadua.cliente;

import com.tonydpadua.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public Cliente findById(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Object não encontrado! Id: "+id));
    }

}
