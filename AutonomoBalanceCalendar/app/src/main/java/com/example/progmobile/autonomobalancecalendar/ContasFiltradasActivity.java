package com.example.progmobile.autonomobalancecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        //pegando o valor total do periodo selecionado
        Double total=0.0;
        for(int i=0;i<contas.size();i++){
            total = total + contas.get(i).getValor();
        }

        TextView valorTotal = (TextView) findViewById(R.id.filtradas_total);
        valorTotal.setText(Double.toString(total));

        ContasAdapter adapter = new ContasAdapter(this, contas);

        Toast.makeText(ContasFiltradasActivity.this, "Contas: "+contas.size(), Toast.LENGTH_SHORT).show();
        listaContas.setAdapter(adapter);

    }
}
