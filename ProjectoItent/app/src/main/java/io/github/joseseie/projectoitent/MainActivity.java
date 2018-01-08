package io.github.joseseie.projectoitent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int EXEMPLO_PARAMETRO_ACTIVITY = 0;

    private Button btnChamarBroswer;
    private Button btnFazerLigacao;
    private Button btnExemploParametro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnChamarBroswer = (Button) findViewById(R.id.btnChamarBroswer);
        this.btnFazerLigacao = (Button) findViewById(R.id.btnFazerLigacao);
        this.btnExemploParametro = (Button) findViewById(R.id.btnExemploParametro);

        this.btnChamarBroswer.setOnClickListener(this);
        this.btnFazerLigacao.setOnClickListener(this);
        this.btnExemploParametro.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if(view == btnChamarBroswer)
        {
            Intent intent = new Intent(this, ChamarBrowser.class );
            startActivity(intent);

        }
        else if (view == btnFazerLigacao)
        {
            Intent intent = new Intent(this, FazerLigacao.class );
            startActivity(intent);

        }
        else if (view == btnExemploParametro)
        {
            Intent intent = new Intent(this, ExemploParametroActivity.class );
            startActivityForResult(intent, EXEMPLO_PARAMETRO_ACTIVITY);
        }
    }
}
