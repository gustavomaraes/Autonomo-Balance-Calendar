package com.example.progmobile.autonomobalancecalendar.modelo;

import java.io.Serializable;

/**
 * Created by gustavo on 30/06/2017.
 */

public class data implements Serializable {

    private int dia;
    private int mes;
    private int ano;

    public data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean maior(data inicio) {

        if(this.getAno() > inicio.getAno()){
            return true;
        } else if(this.getAno() == inicio.getAno()){
            if(this.getMes() > inicio.getMes()){
                return true;
            } else if(this.getMes() == inicio.getMes()){
                if(this.getDia() >= inicio.getDia()){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
