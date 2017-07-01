package com.example.progmobile.autonomobalancecalendar.modelo;

import java.util.Date;

/**
 * Created by gustavo on 30/06/2017.
 */

public class Conta {

    private Long id;
    private String descricao;
    private double valor;
    private data data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public data getData() {
        return data;
    }

    public void setData(data data) {
        this.data = data;
    }
}
