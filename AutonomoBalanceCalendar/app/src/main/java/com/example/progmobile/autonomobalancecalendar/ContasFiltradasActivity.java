package com.example.progmobile.autonomobalancecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.progmobile.autonomobalancecalendar.adapter.ContasAdapter;
import com.example.progmobile.autonomobalancecalendar.banco.Conecta;
import com.example.progmobile.autonomobalancecalendar.modelo.Conta;
import com.example.progmobile.autonomobalancecalendar.modelo.data;

import java.util.List;

public class ContasFiltradasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas_filtradas);

        ListView listaContas = (ListView) findViewById(R.id.lista_contas_filtradas);

        Intent intent = getIntent();
        data inicio = (data) intent.getSerializableExtra("data_inicio");
        data fim = (data) intent.getSerializableExtra("data_fim");

        Conecta conecta = new Conecta(this);
        List<Conta> contas = conecta.buscaPeriodo(inicio, fim);
        conecta.close();

        ContasAdapter adapter = new ContasAdapter(this, contas);

        listaContas.setAdapter(adapter);
    }
}
