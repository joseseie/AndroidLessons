package io.github.joseseie.projectoitent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChamarBrowser extends AppCompatActivity implements View.OnClickListener{

    private Button btnAbrirBrowser;
    private EditText edtUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamar_browser);

        this.btnAbrirBrowser    = (Button) findViewById(R.id.btnAbrirBrowser);
        this.edtUrl             = (EditText) findViewById(R.id.edtUrl);

        btnAbrirBrowser.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        try {
            Uri uri = Uri.parse(edtUrl.getText().toString());

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            startActivity(intent);

        } catch (Exception ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Url Invalida");
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }
    }
}
