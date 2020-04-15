package com.tonydpadua.cliente;

import com.tonydpadua.validation.ClienteUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ClienteUpdate
public class ClienteDTO {

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5,max = 100,message = "O tamanho deve ser entre 5 e 100 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email
    private String email;

    public ClienteDTO(Cliente obj){
        id=obj.getId();
        nome=obj.getNome();
        email=obj.getEmail();
    }

}
