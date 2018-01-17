package io.github.joseseie.fireapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail            = (EditText) findViewById(R.id.edtEmail);
        edtPassword         = (EditText) findViewById(R.id.edtPassword);
        btnLogin            = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if ( firebaseAuth.getCurrentUser() != null)
                {
                    startActivity( new Intent( Login.this, AccountActivity.class));
                }

            }
        };

    }

    @Override
    public void onClick(View view) {

        if (view == btnLogin)
        {
            startSignIn();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener( mAuthListener );
    }

    private void startSignIn()
    {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty( password ))
        {
            Messages.toast(this, "Os campos estao vazios");
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if( !task.isSuccessful())
                    {
                        Messages.toast(Login.this, "Sign in problem");
                    }
                    else
                    {
                        startActivity( new Intent( Login.this, AccountActivity.class));
                    }

                }
            });
        }
    }

}
