package io.github.joseseie.exspinnerlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class ActMain extends AppCompatActivity {

    private EditText edtValor;
    private Spinner spnOpcoes;
    private Button btnAdicionar;
    private Button btnExcluir;
    private ListView lstDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }
}
