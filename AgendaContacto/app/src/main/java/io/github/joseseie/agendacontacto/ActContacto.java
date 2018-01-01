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


public class ActContacto extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;
    private ArrayAdapter<String> adpContatos;

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

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setNeutralButton("Ok",null);

        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            repositorioContato.testeInserirContatos();

            adpContatos = repositorioContato.buscaContatos(this);

            dlg.setMessage("Conexao criada com sucesso");

        } catch (SQLException ex) {

            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());

        }

        dlg.show();

    }

    public void onClick(View v)
    {
        Intent it = new Intent(this, ActCadContactos.class);

        startActivity(it);

    }

}
