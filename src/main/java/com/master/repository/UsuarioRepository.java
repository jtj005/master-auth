package com.master.repository;

import com.master.model.AccountCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UsuarioRepository extends JpaRepository<AccountCredentials,Long>{
    public AccountCredentials findAccountCredentialsByUsernameAndPassword(String email,String senha);

    public AccountCredentials findAccountCredentialsByUsername(String email);
}
