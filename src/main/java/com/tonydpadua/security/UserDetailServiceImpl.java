package com.tonydpadua.security;

import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.cliente.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente==null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(cliente.getId(),cliente.getEmail(),cliente.getSenha(),cliente.getPerfil());

    }
}
