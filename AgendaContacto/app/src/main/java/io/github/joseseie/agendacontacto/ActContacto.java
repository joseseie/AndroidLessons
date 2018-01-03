package io.github.joseseie.agendacontacto;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import android.database.sqlite.*;
import android.database.*;

import io.github.joseseie.agendacontacto.database.DataBase;
import io.github.joseseie.agendacontacto.dominio.RepositorioContato;
import io.github.joseseie.agendacontacto.dominio.entidades.Contato;


public class ActContacto extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;
    private ArrayAdapter<Contato> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contacto);

        btnAdicionar    = (ImageButton) findViewById(R.id.btnAdicionar);
        edtPesquisa     = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos     = (ListView) findViewById(R.id.lstContactos);

        btnAdicionar.setOnClickListener(this);
        lstContatos.setOnItemClickListener(this);



        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);



            adpContatos = repositorioContato.buscaContatos(this);

            lstContatos.setAdapter(adpContatos);

        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setNeutralButton("Ok",null);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.show();

        }



    }

    public void onClick(View v)
    {
        Intent it = new Intent(this, ActCadContactos.class);

        startActivityForResult(it,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        adpContatos = repositorioContato.buscaContatos(this);

        lstContatos.setAdapter(adpContatos);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Contato contato = adpContatos.getItem(position);

        Intent it = new Intent(this, ActCadContactos.class);

        it.putExtra("CONTATO", contato );

        startActivityForResult(it,0);

    }
}
