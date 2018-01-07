package io.github.joseseie.projectoitent;

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

        this.btnAbrirBrowser = (Button) findViewById(R.id.btnAbrirBrowser);

    }


    @Override
    public void onClick(View view) {

    }
}
