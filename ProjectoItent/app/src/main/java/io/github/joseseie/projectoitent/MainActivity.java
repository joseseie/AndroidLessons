package io.github.joseseie.projectoitent;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle parametros = null;

        if(data != null) parametros = data.getExtras();

        if(requestCode == EXEMPLO_PARAMETRO_ACTIVITY)
        {

        }
        switch (resultCode)
        {
            case RESULT_OK:
                if(parametros != null)
                {
                    String vallor = parametros.getString("VALOR");
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setMessage("O o valor do parametro eh: " + vallor);
                    dlg.setNeutralButton("Ok", null);
                    dlg.show();

                }
                break;
            case RESULT_CANCELED:

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setMessage("Operacao Cancelada.");
                dlg.setNeutralButton("Ok", null);
                dlg.show();

                break;
        }

    }
}
