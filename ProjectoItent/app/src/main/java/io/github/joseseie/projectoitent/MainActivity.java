package io.github.joseseie.projectoitent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnChamarBroswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnChamarBroswer = (Button) findViewById(R.id.btnChamarBroswer);

        this.btnChamarBroswer.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if(view == btnChamarBroswer)
        {
            Intent intent = new Intent(this, ChamarBrowser.class );
            startActivity(intent);
        }
    }
}
