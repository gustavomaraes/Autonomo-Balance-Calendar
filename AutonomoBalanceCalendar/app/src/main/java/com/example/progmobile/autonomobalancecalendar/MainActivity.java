package com.example.progmobile.autonomobalancecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.progmobile.autonomobalancecalendar.adapter.ContasAdapter;
import com.example.progmobile.autonomobalancecalendar.banco.Conecta;
import com.example.progmobile.autonomobalancecalendar.modelo.Conta;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaContas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContas = (ListView) findViewById(R.id.lista_pagamentos);

        listaContas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listaContas, View item, int position, long id) {
                Conta conta = (Conta) listaContas.getItemAtPosition(position);

                Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("conta", conta);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novaConta = (Button) findViewById (R.id.nova_entrada);
        novaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)  {
                Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaContas);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista(){
        Conecta conecta = new Conecta(this);
        List<Conta> contas = conecta.busca();
        conecta.close();

        ContasAdapter adapter = new ContasAdapter(this, contas);

        listaContas.setAdapter(adapter);
    }
}