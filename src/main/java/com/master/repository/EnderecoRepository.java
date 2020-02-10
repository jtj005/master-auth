package com.master.repository;

import com.master.model.AccountCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface EnderecoRepository extends JpaRepository<AccountCredentials,Long>{

}
