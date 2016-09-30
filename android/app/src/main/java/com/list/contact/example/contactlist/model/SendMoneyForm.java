package com.list.contact.example.contactlist.model;

/**
 * Created by wesley on 9/1/16.
 */
public class SendMoneyForm {
    private String clienteId;
    private String token;
    private Double valor;

    public SendMoneyForm(String clienteId, String token, Double valor) {
        this.clienteId = clienteId;
        this.token = token;
        this.valor = valor;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
