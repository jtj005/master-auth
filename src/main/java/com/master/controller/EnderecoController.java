package com.master.controller;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.master.model.Endereco;
import com.master.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/buscacep/{cep}")
    @ResponseBody
    public ViaCEPEndereco buscacep(@PathVariable(value = "cep") String cep) {
        try {
            ViaCEPClient viaCEPClient = new ViaCEPClient();
            ViaCEPEndereco viaCEPEndereco = viaCEPClient.getEndereco(cep);
            return  viaCEPEndereco;

        }
        catch (Exception e){
            return null;
        }
    }

}
