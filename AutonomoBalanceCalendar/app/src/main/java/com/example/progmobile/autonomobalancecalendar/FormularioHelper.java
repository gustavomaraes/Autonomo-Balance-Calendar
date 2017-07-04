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
    private Long id;
    private Conta conta;

    public FormularioHelper(FormularioActivity activity) {
        campoData = (DatePicker) activity.findViewById(R.id.formulario_data);
        campoValor = (EditText) activity.findViewById(R.id.formulario_valor);
        campoDescricao = (EditText) activity.findViewById(R.id.formulario_descricao);
        chaveSwitch = (RadioGroup) activity.findViewById(R.id.formulario_switch);
        conta = new Conta();
    }

    public Conta pegaConta(){
        conta.setId(this.id);
        conta.setDescricao(campoDescricao.getText().toString());
        conta.setValor(Double.parseDouble(campoValor.getText().toString()));
        if(chaveSwitch.getCheckedRadioButtonId() == R.id.formulario_pagamento){
            conta.setValor(conta.getValor() * -1);
        }
        data aux = new data(campoData.getDayOfMonth(), campoData.getMonth(), campoData.getYear());
        conta.setData(aux);
        return conta;
    }

    public void preencheFormulario(Conta conta){
        id = conta.getId();
        campoDescricao.setText(conta.getDescricao());
        campoData.updateDate(conta.getData().getAno(), conta.getData().getMes(), conta.getData().getDia());
        if( conta.getValor() > 0 ){
            chaveSwitch.check(R.id.formulario_recebimento);
            campoValor.setText(String.valueOf(conta.getValor()));
        }else{
            chaveSwitch.check(R.id.formulario_pagamento);
            campoValor.setText(String.valueOf(-1 * conta.getValor()));
        }
    }
}
