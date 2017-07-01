package com.example.progmobile.autonomobalancecalendar;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.progmobile.autonomobalancecalendar.modelo.Conta;
import com.example.progmobile.autonomobalancecalendar.modelo.data;

/**
 * Created by gustavo on 30/06/2017.
 */

public class FormularioHelper {

    private final DatePicker campoData;
    private final EditText campoValor;
    private final EditText campoDescricao;
    private final RadioGroup chaveSwitch;

    private Conta conta;

    public FormularioHelper(FormularioActivity activity) {
        campoData = (DatePicker) activity.findViewById(R.id.formulario_data);
        campoValor = (EditText) activity.findViewById(R.id.formulario_valor);
        campoDescricao = (EditText) activity.findViewById(R.id.formulario_descricao);
        chaveSwitch = (RadioGroup) activity.findViewById(R.id.formulario_switch);
        conta = new Conta();
    }

    public Conta pegaConta(){
        conta.setDescricao(campoDescricao.getText().toString());
        conta.setValor(Double.parseDouble(campoValor.getText().toString()));
        //procurar alguma forma de salvar quando o valor for negativo, usando o check do chaveSwitch
        data aux = new data(campoData.getDayOfMonth(), campoData.getMonth(), campoData.getYear());
        conta.setData(aux);
        return conta;
    }

    public void preencheFormulario(Conta conta){
        campoValor.setText(String.valueOf(conta.getValor()));
        campoDescricao.setText(conta.getDescricao());
        //como dar um set na data?? pesquisar essa informacao
        if( conta.getValor() > 0 ){
            chaveSwitch.check(R.id.formulario_recebimento);
        }else{
            chaveSwitch.check(R.id.formulario_pagamento);
        }
    }

}
