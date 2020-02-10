package com.master.service;

import br.com.moip.jassinaturas.Assinaturas;
import br.com.moip.jassinaturas.clients.attributes.*;
import br.com.moip.jassinaturas.communicators.SandboxCommunicator;
import com.master.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoipResources {

    private static final String TOKEN = "";
    private static final String SECRET = "";

    private static final Assinaturas assinaturas = new Assinaturas(new Authentication(TOKEN, SECRET), new SandboxCommunicator());

    public void criarPlano(Plan plano){
        Plan toCreate = new Plan();
        toCreate
                .withCode("1")
                .withDescription("Decricao do meu plano")
                .withName("Plano do Sucesso")
                .withAmount(1000)
                .withSetupFee(100)
                .withBillingCycles(1)
                .withPlanStatus(PlanStatus.ACTIVE)
                .withMaxQty(10)
                .withInterval(new Interval()
                        .withLength(10)
                        .withUnit(Unit.MONTH))
                .withTrial(new Trial()
                        .withDays(10)
                        .enabled());
        Plan created = assinaturas.plans().create(toCreate);
    }

    public Plan getPlanPorCode(String code){
        return assinaturas.plans().show(code);
    }

    public void ativarPlan(String code){
        Plan plan = assinaturas.plans().active(code);
    }

    public void desativarPlan(String code){
        Plan plan = assinaturas.plans().inactive(code);
    }



    public void criarAssinatura(String codePlan,Pessoa pessoa){
        Subscription toBeCreated = new Subscription();
        toBeCreated
                .withCode(pessoa.getId().toString())
                .withAmount(100)
                .withPlan(
                        new Plan()
                                .withCode(codePlan))
                .withCustomer(pessoa.converteEmCustomer());

        Subscription created = assinaturas.subscriptions().create(toBeCreated);
    }

    public void getsubscriptionPorCode(String code){
        Subscription subscription = assinaturas.subscriptions().activate(code);
    }

    public void cancelarSubscription(String code){
        Subscription subscription = assinaturas.subscriptions().cancel(code);
    }

    public void desativarSubscription(String code){
        Subscription subscription = assinaturas.subscriptions().suspend(code);
    }

    public void ativarSubscription(String code){
        Subscription subscription = assinaturas.subscriptions().activate(code);
    }

    public List<Subscription> listSubscription(){
        return assinaturas.subscriptions().list();
    }

    public Subscription getSubscriptionPorCode(String code){
        return assinaturas.subscriptions().show(code);
    }


    public void criarAssinante(Pessoa pessoa){
        Customer customer = pessoa.converteEmCustomer();
        Customer created = assinaturas.customers().create(customer);
    }

    public void atualizarCartao(BillingInfo billingInfo){
        Customer toUpdate = new Customer();
        toUpdate
                .withCode("CUSTOMER_CODE")
                .withBillingInfo(
                        new BillingInfo()
                                .withCreditCard(
                                        new CreditCard()
                                                .withExpirationMonth("10")
                                                .withExpirationYear("18")
                                                .withHolderName("CARD_HOLDER_NAME")
                                                .withNumber("4111111111111111")));
        Customer updated = assinaturas.customers().updateVault(toUpdate);
    }

    public List<Customer> listCustomers(){
        return  assinaturas.customers().list();
    }

    public Customer getCustomerPorCode(String code){
        return assinaturas.customers().show(code);
    }



}
