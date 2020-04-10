package com.tonydpadua.cidade;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tonydpadua.estado.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cidade implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="estado_id")
    private Estado estado;


}
