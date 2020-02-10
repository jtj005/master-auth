package com.master.controller;

import br.com.moip.jassinaturas.Assinaturas;
import br.com.moip.jassinaturas.clients.attributes.Plan;
import br.com.moip.jassinaturas.clients.attributes.Subscription;
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
@RequestMapping("/assinatura")
public class AssinaturaController {

    @Autowired
    private MoipResources moipResources;


    @GetMapping("/plano/novo")
    @ResponseBody
    public void novoPlano(){
        moipResources.criarPlano(new Plan());
    }

    @GetMapping("/plano/listall")
    @ResponseBody
    public Plan listPlanos(){
        return moipResources.getPlanPorCode("1");
    }

    @GetMapping("/listall")
    @ResponseBody
    public List listAll(){
        return moipResources.listSubscription();
    }

    @GetMapping("/getOne/{id}")
    public Subscription getOne(@PathVariable(value = "id") String id) {
        return moipResources.getSubscriptionPorCode(id);
    }


}
