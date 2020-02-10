package com.master.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="usuarios")
//  USER
public class AccountCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    private String username;


    private String password;

    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    @Enumerated
    private StatusUsuario statusUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuario statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
