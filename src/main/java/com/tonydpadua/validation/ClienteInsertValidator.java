package com.tonydpadua.validation;

import com.tonydpadua.cliente.Cliente;
import com.tonydpadua.cliente.ClienteNewDTO;
import com.tonydpadua.cliente.ClienteRepository;
import com.tonydpadua.cliente.TipoCliente;
import com.tonydpadua.exceptions.FieldMessage;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@AllArgsConstructor
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfouCnpj","CPF Invalido"));
        }

        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfouCnpj","CNPJ Invalido"));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux!=null){
            list.add(new FieldMessage("email","Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}

