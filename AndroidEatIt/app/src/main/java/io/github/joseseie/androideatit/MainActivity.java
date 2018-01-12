package io.github.joseseie.androideatit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn   = (Button) findViewById(R.id.btnSignIn);
        btnSignUp   = (Button) findViewById(R.id.btnSignUp);

        txtSlogan   = (TextView) findViewById(R.id.txtSlogan);


    }
}
