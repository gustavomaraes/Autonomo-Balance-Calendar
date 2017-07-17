package com.example.progmobile.autonomobalancecalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.progmobile.autonomobalancecalendar.R;
import com.example.progmobile.autonomobalancecalendar.modelo.Conta;

import java.util.List;

/**
 * Created by gustavo on 03/07/2017.
 */

public class ContasAdapter extends BaseAdapter {
    private final List<Conta> contas;
    private final Context context;

    public ContasAdapter(Context context, List<Conta> contas) {
        this.contas = contas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contas.size();
    }

    @Override
    public Object getItem(int position) {
        return contas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Conta conta = contas.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoDescricao = (TextView) view.findViewById(R.id.item_descricao);
        campoDescricao.setText(conta.getDescricao());

        TextView campoValor = (TextView) view.findViewById(R.id.item_valor);
        campoValor.setText(String.valueOf(conta.getValor()));


        TextView campoData = (TextView) view.findViewById(R.id.item_data);
        campoData.setText(String.valueOf(conta.getData().getDia() + "/" + (conta.getData().getMes()+1) + "/" + conta.getData().getAno()));

        return view;
    }
}
