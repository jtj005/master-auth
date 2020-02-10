package com.master.service;

import com.master.model.AccountCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LoginService implements  UserDetailsService {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountCredentials accountCredentials = usuarioService.findAccountCredentialsByUsername(s);

            if(accountCredentials == null){
                throw new UsernameNotFoundException("Invalid username or password.");
            }
            return new org.springframework.security.core.userdetails.User(accountCredentials.getId().toString(), accountCredentials.getPassword(), Collections.emptyList());
    }
}
