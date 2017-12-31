package io.github.joseseie.agendacontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;


public class ActContacto extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contacto);

        btnAdicionar = (ImageButton) findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(this);


    }

    public void onClick(View v)
    {
        Intent it = new Intent(this, ActCadContactos.class);

        startActivity(it);

    }

}
