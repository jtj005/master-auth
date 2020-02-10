package com.master.model;


import br.com.moip.jassinaturas.clients.attributes.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nome;

    private LocalDateTime dataHoraCadastro = LocalDateTime.now();

    private LocalDateTime dataHoraUltimaAlteracao;

    private Integer sexo; //0: homem 1: mulher

    private LocalDate dataNascimento;

    private String cpf;

    private String rg;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefone> telefone;

    @OneToOne(mappedBy="pessoa")
    private AccountCredentials accountCredentials;


    @Transient
    private br.com.moip.jassinaturas.clients.attributes.BillingInfo billingInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public LocalDateTime getDataHoraUltimaAlteracao() {
        return dataHoraUltimaAlteracao;
    }

    public void setDataHoraUltimaAlteracao(LocalDateTime dataHoraUltimaAlteracao) {
        this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public AccountCredentials getAccountCredentials() {
        return accountCredentials;
    }

    public void setAccountCredentials(AccountCredentials accountCredentials) {
        this.accountCredentials = accountCredentials;
    }


public Customer converteEmCustomer(){
            Customer customer = new Customer()
                .withCode(this.getId().toString())
                .withBirthdate(
                        new Birthdate()
                                .withDay(this.getDataNascimento().getDayOfMonth())
                                .withMonth(Month.getMonth(this.getDataNascimento().getMonthValue()))
                                .withYear(this.getDataNascimento().getYear()))
                .withCpf(this.getCpf())
                .withEmail(this.getAccountCredentials().getUsername())
                .withFullname(this.getNome())
                .withPhoneAreaCode(this.getTelefone().get(0).getNumero().substring(0,1))
                .withPhoneNumber(this.getTelefone().get(0).getNumero().substring(2,this.getTelefone().get(0).getNumero().length()))
                .withAddress(
                        new Address()
                                .withCity(this.getEndereco().getLocalidade())
                                .withComplement(this.getEndereco().getComplemento())
                                .withCountry(Country.BRA)
                                .withDistrict(this.getEndereco().getBairro())
                                .withNumber("")
                                .withState(State.valueOf(this.getEndereco().getUf()))
                                .withStreet(this.getEndereco().getLogradouro())
                                .withZipcode(this.getEndereco().getCep()))
                .withBillingInfo(
                        new BillingInfo()
                                .withCreditCard(new CreditCard()
                                        .withExpirationMonth(this.getBillingInfo().getCreditCard().getExpirationMonth())
                                        .withExpirationYear(this.getBillingInfo().getCreditCard().getNumber())
                                        .withHolderName(this.getBillingInfo().getCreditCard().getHolderName())
                                        .withNumber(this.getBillingInfo().getCreditCard().getNumber())));
            return customer;
}

}
