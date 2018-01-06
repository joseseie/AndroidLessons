package io.github.joseseie.agendacontacto;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import android.view.*;

import android.database.sqlite.*;
import android.database.*;

import io.github.joseseie.agendacontacto.app.MessageBox;
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
    private FiltraDados filtraDados;

    public static final String PAR_CONTATO = "CONTATO";

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

            filtraDados = new FiltraDados(adpContatos);
            edtPesquisa.addTextChangedListener(filtraDados);

        } catch (SQLException ex) {

            MessageBox.show(this,"Erro","Erro ao criar o banco " + ex.getMessage() );

        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(conn != null) conn.close();
    }

    public void onClick(View v)
    {
        Intent it = new Intent(this, ActCadContactos.class);

        startActivityForResult(it,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        adpContatos = repositorioContato.buscaContatos(this);
        filtraDados.setArrayAdapter(adpContatos);
        lstContatos.setAdapter(adpContatos);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Contato contato = adpContatos.getItem(position);

        Intent it = new Intent(this, ActCadContactos.class);

        it.putExtra(PAR_CONTATO, contato );

        startActivityForResult(it,0);

    }

    private class FiltraDados implements TextWatcher {

        private ArrayAdapter<Contato> arrayAdapter;
        private FiltraDados(ArrayAdapter<Contato> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }

        public void setArrayAdapter(ArrayAdapter<Contato> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            this.arrayAdapter.getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
