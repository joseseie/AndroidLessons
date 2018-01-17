package io.github.joseseie.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button btnSaveToFirebase, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSaveToFirebase   = (Button) findViewById(R.id.btnSaveToFirebase);
        btnLogin            = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        btnSaveToFirebase.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String msg = "Nada foi clicado.";
        if (view == btnLogin)
        {
            msg = "Clicou btnLogin";
            this.startMyActivity( msg, Login.class );

        }
        else if (view == btnSaveToFirebase)
        {
            msg = "Clicou btnSaveToFirebase";
            this.startMyActivity( msg, SaveToFirebaseActivity.class );

        }


    }

    private void startMyActivity(String msg, Class<?> clas)
    {
        Intent intent = new Intent(this, clas);
        startActivity( intent );

//        Toast.makeText(this, msg , Toast.LENGTH_LONG).show();
    }

}
