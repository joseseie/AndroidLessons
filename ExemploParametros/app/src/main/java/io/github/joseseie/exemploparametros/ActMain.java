package io.github.joseseie.exemploparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ActMain extends AppCompatActivity implements View.OnClickListener {

    private EditText edtValor;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);


        btnOk.setOnClickListener(this);

        edtValor = (EditText) findViewById(R.id.edtValor);
        btnOk = (Button) findViewById(R.id.btnOk);


    }

    public void onClick(View v)
    {
        Intent it = new Intent(this,ActSeungaTela.class);

        it.putExtra("VALOR",edtValor.getText().toString());

        startActivity(it);

    }


}
