package io.github.joseseie.agendacontacto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.github.joseseie.agendacontacto.dominio.entidades.Contato;

/**
 * Created by Jose Seie on 1/7/2018.
 */

public class ContatoArrayAdapter extends ArrayAdapter<Contato> {

    private int resource = 0;
    private LayoutInflater inflater;

    public ContatoArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;
        ViewHolder viewHolder = null;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            view = inflater.inflate( resource, parent,false);

            viewHolder.txtColor = (TextView) view.findViewById(R.id.txtCor);
            viewHolder.txtNome = (TextView) view.findViewById(R.id.txtNome);
            viewHolder.txtTelefone = (TextView) view.findViewById(R.id.txtTelefone);

            view.setTag(viewHolder);

            convertView = view;

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Contato contato = getItem(position);

        //viewHolder.txtColor
        viewHolder.txtNome.setText(contato.getNome());
        viewHolder.txtTelefone.setText(contato.getTelefone());

        return view;

    }

    static class ViewHolder
    {
        TextView txtColor;
        TextView txtNome;
        TextView txtTelefone;
    }



}
