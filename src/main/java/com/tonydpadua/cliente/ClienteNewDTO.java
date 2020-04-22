package com.tonydpadua.cliente;

import com.tonydpadua.validation.ClienteInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@NoArgsConstructor
@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5,max = 100,message = "O tamanho deve ser entre 5 e 100 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;
    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String senha;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;
    private String complemento;
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;
}
