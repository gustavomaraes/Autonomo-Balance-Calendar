package com.example.progmobile.autonomobalancecalendar.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progmobile.autonomobalancecalendar.modelo.Conta;
import com.example.progmobile.autonomobalancecalendar.modelo.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 03/07/2017.
 */

public class Conecta extends SQLiteOpenHelper{

    public Conecta(Context context) {
        super(context, "Contas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contas(" +
                "id INTEGER PRIMARY KEY, " +
                "descricao TEXT, " +
                "valor REAL, " +
                "dia INTEGER, " +
                "mes INTEGER, " +
                "ano INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insere(Conta conta){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDados(conta);

        db.insert("Contas", null, dados);
    }

    private ContentValues pegaDados(Conta conta){
        ContentValues dados = new ContentValues();

        dados.put("descricao", conta.getDescricao());
        dados.put("valor", conta.getValor());
        dados.put("dia", conta.getData().getDia());
        dados.put("mes", conta.getData().getMes());
        dados.put("ano", conta.getData().getAno());

        return dados;
    }

    public List<Conta> busca(){
        String sql = "SELECT * FROM Contas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Conta> contas = new ArrayList<Conta>();

        while(c.moveToNext()){
            Conta conta = new Conta();
            conta.setId(c.getLong(c.getColumnIndex("id")));
            conta.setDescricao(c.getString(c.getColumnIndex("descricao")));
            conta.setValor(c.getDouble(c.getColumnIndex("valor")));
            data d = new data(c.getInt(c.getColumnIndex("dia")), c.getInt(c.getColumnIndex("mes")), c.getInt(c.getColumnIndex("ano")));
            conta.setData(d);

            contas.add(conta);
        }

        c.close();

        return contas;
    }

    public void deleta(Conta conta){
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {conta.getId().toString()};
        db.delete("Contas", "id = ?", params);
    }

    public void altera(Conta conta){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDados(conta);

        String[] params = {conta.getId().toString()};
        db.update("Contas", dados, "id = ?", params);
    }

    public List<Conta> buscaPeriodo(data inicio, data fim){
        String sql = "SELECT * FROM Contas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Conta> contas = new ArrayList<Conta>();

        while(c.moveToNext()){
            Conta conta = new Conta();
            conta.setId(c.getLong(c.getColumnIndex("id")));
            conta.setDescricao(c.getString(c.getColumnIndex("descricao")));
            conta.setValor(c.getDouble(c.getColumnIndex("valor")));
            data d = new data(c.getInt(c.getColumnIndex("dia")), c.getInt(c.getColumnIndex("mes")), c.getInt(c.getColumnIndex("ano")));
            conta.setData(d);

            if( d.maior(inicio) && fim.maior(d) ) {
                contas.add(conta);
            }
        }

        c.close();

        return contas;
    }
}
