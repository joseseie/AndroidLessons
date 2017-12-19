package io.github.joseseie.calculadorabasica;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class ActMain extends AppCompatActivity implements View.OnClickListener{

    /*
    * Nota: Para acao de click usanos View.OnClickListener
    */

//    Criacao de objectos (By Seie)
    private EditText edtValor1;
    private EditText edtValor2;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

//        Recuperacao dos valores nas telas

        edtValor1 = (EditText) findViewById(R.id.edtValor1);
        edtValor2 = (EditText) findViewById(R.id.edtValor2);
        btnCalcular =  (Button) findViewById(R.id.btnCalcular);

//        Outra forma de vincular o evento onClick.
//        btnCalcular.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){}
//        });

        btnCalcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        String v1 = this.edtValor1.getText().toString();
        String v2 = this.edtValor2.getText().toString();

        if(v1.trim().isEmpty() || v2.trim().isEmpty())
        {
            dlg.setMessage("Por favor preencha todos campos");
            dlg.setNeutralButton("Ok", null);

        } else {

            double valor1 = Double.parseDouble(v1);
            double valor2 = Double.parseDouble(v2);

            double resultado = valor1 + valor2;

            dlg.setMessage("O resultado eh: " + resultado);
            dlg.setNeutralButton("Ok", null);

        }

        dlg.show();

    }

}
