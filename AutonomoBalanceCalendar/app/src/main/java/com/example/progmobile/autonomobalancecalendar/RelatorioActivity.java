package com.example.progmobile.autonomobalancecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.progmobile.autonomobalancecalendar.modelo.data;

public class RelatorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        Button novaConta = (Button) findViewById(R.id.relatorio_gera);
        novaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)  {
                Intent intentVaiProFormulario = new Intent(RelatorioActivity.this, ContasFiltradasActivity.class);

                DatePicker di = (DatePicker) findViewById(R.id.relatorio_inicio);
                data data_inicio = new data(di.getDayOfMonth(), di.getMonth(), di.getYear());
                DatePicker df = (DatePicker) findViewById(R.id.relatorio_fim);
                data data_fim = new data(df.getDayOfMonth(), df.getMonth(), df.getYear());

                intentVaiProFormulario.putExtra("data_inicio", data_inicio);
                intentVaiProFormulario.putExtra("data_fim", data_fim);
                startActivity(intentVaiProFormulario);
            }
        });
    }
}
