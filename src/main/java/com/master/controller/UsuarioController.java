package com.master.controller;

import com.master.model.AccountCredentials;
import com.master.service.MoipResources;
import com.master.service.UsuarioService;
import com.master.utils.EmailUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MoipResources moipResources;

    @PostMapping("/create")
    public AccountCredentials create(@RequestBody AccountCredentials usuario) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        return usuarioService.save(usuario);
    }

    @PostMapping("/token")
    public void getToken(@RequestBody AccountCredentials usuario) {

    }

    @GetMapping("/realizarAssinatura/{id}")
    public ResponseEntity<?> realizaAssinatura(@PathVariable(value = "id") Long id) {
        Optional<AccountCredentials> Usuario = usuarioService.findById(id);
        moipResources.criarAssinatura("1",Usuario.get().getPessoa());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recoverPassword/{email}")
    @ResponseBody
    public String recuperarSenha(@PathVariable(value = "email") String email){
        try {
        AccountCredentials accountCredentials = usuarioService.findAccountCredentialsByUsername(email);
        String novaSenha = EmailUtils.geradorDeSenhaDeRecuperacao();
        accountCredentials.setPassword(novaSenha);

        EmailUtils.enviarEmailRecurepacaoSenha(accountCredentials);
        accountCredentials.setPassword(new BCryptPasswordEncoder().encode(novaSenha));
        usuarioService.save(accountCredentials);


        return "Email de troca de senha enviado";
        } catch (EmailException e) {
            return "Erro";
        }
    }

    @GetMapping("/listall")
    @ResponseBody
    public List listAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/getOne/{id}")
    public Optional<AccountCredentials> getOne(@PathVariable(value = "id") Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/update/{id}")
    public AccountCredentials update(@PathVariable(value = "id") Long id, @Valid @RequestBody AccountCredentials usuario) {
        Optional<AccountCredentials> usuarioResgatado = usuarioService.findById(id);
        usuario = usuarioResgatado.get();
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Optional<AccountCredentials> usuarioResgatado = usuarioService.findById(id);

        usuarioService.delete(usuarioResgatado.get());

        return ResponseEntity.ok().build();
    }
}
