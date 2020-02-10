package com.master.model;

public enum StatusUsuario {
    CADASTRADO_NAO_PAGO("red","Pagamento Pedente"),
    CADASTRADO_PAGO("green","Pago");

    private final String cor;
    private final String descricao;

    private StatusUsuario(String cor, String descricao) {
        this.cor=cor;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCor() {
        return cor;
    }
}


