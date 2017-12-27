package io.github.joseseie.exemploparametros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ActSeungaTela extends AppCompatActivity {

    private EditText edtValor;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_seunga_tela);

        edtValor = (EditText) findViewById(R.id.edtValor);
        btnFechar = (Button) findViewById(R.id.btnFechar);

        Bundle bundle = getIntent().getExtras();



    }
}
