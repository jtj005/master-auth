package com.master.utils;

import com.master.model.AccountCredentials;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.Random;

public class EmailUtils {

    private static Random rand = new Random();
    private static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ123456789".toCharArray();

    public static String geradorDeSenhaDeRecuperacao(){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i <20; i++) {
                int ch = rand.nextInt (letras.length);
                sb.append (letras [ch]);
            }
            return sb.toString();
    }

    public static void enviarEmailRecurepacaoSenha(AccountCredentials accountCredentials) throws EmailException {
        SimpleEmail mail = new SimpleEmail();
        mail.setFrom("jtjr005@gmail.com", "Meu nome");
        mail.setSubject("Recuperação de Senha");
        mail.setMsg("Senha temporaria para ser substituida: "+accountCredentials.getPassword());
        mail.setSSLOnConnect(true);
        mail.setAuthentication("jtjr005@gmail.com", "");
        mail.setHostName("smtp.gmail.com");
        mail.setSmtpPort(465);
        mail.addTo(""+accountCredentials.getUsername(), ""+accountCredentials.getUsername());
        mail.send();
    }
}
