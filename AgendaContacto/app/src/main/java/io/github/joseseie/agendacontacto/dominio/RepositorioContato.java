package io.github.joseseie.agendacontacto.dominio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

/**
 * Created by Jose Seie on 12/31/2017.
 */

public class RepositorioContato {

    private SQLiteDatabase conn;


    public RepositorioContato(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public ArrayAdapter<String> buscaContatos(Context context)
    {
        ArrayAdapter<String> adpContactos = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        //Continuar no minuto 20, no video 13.

        return adpContactos;
    }

}
