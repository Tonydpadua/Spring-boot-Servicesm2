package com.tonydpadua.security;

import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.cliente.ClienteRepository;
import com.tonydpadua.email.EmailService;
import com.tonydpadua.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private ClienteRepository clienteRepository;

    private BCryptPasswordEncoder pe;

    private EmailService emailService;

    public void sendNewPassword(String email){
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente==null){
            throw new ObjectNotFoundException("Email não encontrado");
        }
        String newPass = passwordGenerator();
        cliente.setSenha(pe.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente,newPass);
    }


    public String passwordGenerator(){
        String passwordList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%¨&*()";
        char[] password = new char[10];
        for(int i=0;i<10;i++){
            int rand =(int) (Math.random()*passwordList.length());
            password[i] = passwordList.charAt(rand);
        }
        return new String(password);
    }

}
