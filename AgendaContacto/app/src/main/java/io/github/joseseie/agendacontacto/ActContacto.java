package io.github.joseseie.agendacontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;


public class ActContacto extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contacto);

        btnAdicionar    = (ImageButton) findViewById(R.id.btnAdicionar);
        edtPesquisa     = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos     = (ListView) findViewById(R.id.lstContactos);



        btnAdicionar.setOnClickListener(this);


    }

    public void onClick(View v)
    {
        Intent it = new Intent(this, ActCadContactos.class);

        startActivity(it);

    }

}
