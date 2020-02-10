package com.master.service;

import com.master.model.AccountCredentials;
import com.master.model.Pessoa;
import com.master.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;



}
