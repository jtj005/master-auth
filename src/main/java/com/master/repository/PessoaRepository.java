package com.master.repository;

import com.master.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa,Long> {



}
