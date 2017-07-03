package com.example.progmobile.autonomobalancecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.progmobile.autonomobalancecalendar.banco.Conecta;
import com.example.progmobile.autonomobalancecalendar.modelo.Conta;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Conta conta = (Conta) intent.getSerializableExtra("conta");
        if (conta != null){
            helper.preencheFormulario(conta);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Conta conta = new Conta();
        Conecta conecta = new Conecta(this);

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                conta = helper.pegaConta();

                if (conta.getId() != null) {
                    conecta.altera(conta);
                } else {
                    conecta.insere(conta);
                }
                conecta.close();

                Toast.makeText(FormularioActivity.this, "Conta salva!", Toast.LENGTH_SHORT).show();

                finish();
                break;

            case R.id.menu_formulario_excluir:
                conecta.deleta(conta);
                conecta.close();
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
