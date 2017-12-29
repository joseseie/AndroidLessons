package io.github.joseseie.exspinnerlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class ActMain extends AppCompatActivity implements View.OnClickListener {

    private EditText edtValor;
    private Spinner spnOpcoes;
    private Button btnAdicionar;
    private Button btnExcluir;
    private ListView lstDados;

    private ArrayAdapter<String> adpOpcoes;
    private ArrayAdapter<String> adpDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        edtValor = (EditText) findViewById(R.id.edtValor);
        spnOpcoes = (Spinner) findViewById(R.id.spnOpcoes);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        lstDados = (ListView) findViewById(R.id.lsvDados);

        btnExcluir.setOnClickListener(this);
        btnAdicionar.setOnClickListener(this);


        adpOpcoes = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOpcoes.setAdapter(adpOpcoes);

        adpDados.add("Opcao 1");
        adpDados.add("Opcao 2");
        adpDados.add("Opcao 3");
        adpDados.add("Opcao 4");

        adpDados = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lstDados.setAdapter(adpDados);
    }

    @Override
    public void onClick(View v)
    {
        if(v == btnAdicionar)
        {
            String item = edtValor.getText().toString();

            item += " - " + spnOpcoes.getSelectedItem();

            adpDados.add(item);

        }
        else
        {
            if(v == btnExcluir)
            {
                if(adpDados.getCount() > 0)
                {
                    String item = adpDados.getItem(0);
                    adpDados.remove(item);

                }
            }
        }

    }

}
