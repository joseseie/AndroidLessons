package io.github.joseseie.agendacontacto.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

import io.github.joseseie.agendacontacto.ContatoArrayAdapter;
import io.github.joseseie.agendacontacto.R;
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

    private ContentValues preencheContentValues(Contato contato)
    {
        ContentValues values = new ContentValues();
        values.put(Contato.NOME, contato.getNome());
        values.put(Contato.TELEFONE, contato.getTelefone());
        values.put(Contato.TIPOTELEFONE, contato.getTipoTelefone());
        values.put(Contato.EMAIL, contato.getEmail());
        values.put(Contato.TIPOEMAIL, contato.getTipoEmail());
        values.put(Contato.ENDERECO, contato.getEndereco());
        values.put(Contato.TIPOENDERECO, contato.getTipoEndereco());
        values.put(Contato.DATASESPECIAIS, contato.getDatasEspeciais().getTime());
        values.put(Contato.TIPODATASESPECIAIS, contato.getTipoDatasEspeciais());
        values.put(Contato.GRUPOS, contato.getGrupos());

        return values;
    }

    public void excluir(long id)
    {
        conn.delete( Contato.TABELA,"ID = ? ", new String[]{ String.valueOf( id ) });
    }

    public void inserir(Contato contato)
    {
        ContentValues values = this.preencheContentValues(contato);

        conn.insertOrThrow( Contato.TABELA,null,values);
    }


    public void alterar(Contato contato)
    {
        if(contato.getId() > 0 )
        {
            ContentValues values = this.preencheContentValues(contato);

            String[] params = { String.valueOf(contato.getId()) };

            conn.update( Contato.TABELA ,values," ID = ? ",params);
        }
    }

    public ContatoArrayAdapter buscaContatos(Context context)
    {
        ContatoArrayAdapter adpContactos = new ContatoArrayAdapter(context, R.layout.item_contato);

        Cursor cursor = conn.query( Contato.TABELA ,null,null,null,null,null,null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            do {

                Contato  contato = new Contato();

                contato.setId(cursor.getLong( cursor.getColumnIndex(Contato.ID) ));
                contato.setNome(cursor.getString( cursor.getColumnIndex(Contato.NOME) ));
                contato.setTelefone(cursor.getString( cursor.getColumnIndex(Contato.TELEFONE) ));
                contato.setTipoTelefone(cursor.getString( cursor.getColumnIndex(Contato.TIPOTELEFONE) ));
                contato.setEmail(cursor.getString( cursor.getColumnIndex(Contato.EMAIL) ));
                contato.setTipoEmail(cursor.getString( cursor.getColumnIndex(Contato.TIPOEMAIL) ));
                contato.setEndereco(cursor.getString( cursor.getColumnIndex(Contato.ENDERECO) ));
                contato.setTipoEndereco(cursor.getString( cursor.getColumnIndex(Contato.TIPOENDERECO) ));
                contato.setDatasEspeciais(new Date(cursor.getLong( cursor.getColumnIndex(Contato.DATASESPECIAIS) )));
                contato.setTipoDatasEspeciais(cursor.getString( cursor.getColumnIndex(Contato.TIPODATASESPECIAIS) ));
                contato.setGrupos(cursor.getString( cursor.getColumnIndex(Contato.GRUPOS) ));


                adpContactos.add(contato);

            } while (cursor.moveToNext());

        }

        return adpContactos;
    }

}
