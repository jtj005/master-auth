package com.master.model;


import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco extends ViaCEPEndereco{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


}
