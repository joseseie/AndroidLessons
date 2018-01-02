package io.github.joseseie.agendacontacto.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

import io.github.joseseie.agendacontacto.dominio.entidades.Contato;

/**
 * Created by Jose Seie on 12/31/2017.
 */

public class RepositorioContato {

    private SQLiteDatabase conn;


    public RepositorioContato(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public void inserir(Contato contato)
    {
        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipoTelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoEmail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoEndereco());
        values.put("DATASESPECIAIS", contato.getDatasEspeciais().getTime());
        values.put("TIPODATAESPECIAIS", contato.getTipoDatasEspeciais());
        values.put("GRUPOS", contato.getGrupos());


        conn.insertOrThrow("CONTATO",null,values);
    }

    public void testeInserirContatos(){

        for (int i = 0; i < 10; i ++) {
            ContentValues values = new ContentValues();
            values.put("TELEFONE", "84543752" + i);
            conn.insertOrThrow("CONTATO",null,values);
        }

    }

    public ArrayAdapter<Contato> buscaContatos(Context context)
    {
        ArrayAdapter<Contato> adpContactos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("CONTATO",null,null,null,null,null,null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            do {

                Contato  contato = new Contato();

                contato.setId(cursor.getLong( cursor.getColumnIndex("ID") ));
                contato.setNome(cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contato.setTipoEndereco(cursor.getString(7));
                contato.setDatasEspeciais(new Date(cursor.getLong(5)));
                contato.setTipoDatasEspeciais(cursor.getString(9));
                contato.setGrupos(cursor.getString(10));


                adpContactos.add(contato);

            } while (cursor.moveToNext());

        }

        return adpContactos;
    }

}
