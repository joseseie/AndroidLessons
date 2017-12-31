 package io.github.joseseie.agendacontacto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ActCadContactos extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtDatasEspeciais;
    private EditText edtGrupos;

    private Spinner spnTipoEmail;
    private Spinner spnTipoTelefone;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contactos);

        edtNome     = (EditText) findViewById(R.id.edtNome);
        edtEmail     = (EditText) findViewById(R.id.edtEmail);
        edtTelefone     = (EditText) findViewById(R.id.edtTelefone);
        edtEndereco     = (EditText) findViewById(R.id.edtEndereco);
        edtDatasEspeciais     = (EditText) findViewById(R.id.edtDatasEspeciais);
        edtGrupos     = (EditText) findViewById(R.id.edtGrupos);



    }
}
