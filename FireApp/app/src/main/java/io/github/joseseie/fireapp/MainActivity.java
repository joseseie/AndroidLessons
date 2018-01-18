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

    Button btnSaveToFirebase, btnLogin, btnListView, btnImageUpload,
            btnImageUploadShow,btnAudeoRecorder, btnGoogleSignIn,btnOfflineCapable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSaveToFirebase   = (Button) findViewById(R.id.btnSaveToFirebase);
        btnLogin            = (Button) findViewById(R.id.btnLogin);
        btnListView         = (Button) findViewById(R.id.btnListView);
        btnImageUpload      = (Button) findViewById(R.id.btnImageUpload);
        btnImageUploadShow  = (Button) findViewById(R.id.btnDataUpload);
        btnAudeoRecorder    = (Button) findViewById(R.id.btnAudeoRecorder);
        btnGoogleSignIn     = (Button) findViewById(R.id.btnGoogleSignIn);

        btnLogin.setOnClickListener(this);
        btnSaveToFirebase.setOnClickListener(this);
        btnListView.setOnClickListener(this);
        btnImageUpload.setOnClickListener(this);
        btnImageUploadShow.setOnClickListener(this);
        btnAudeoRecorder.setOnClickListener(this);
        btnGoogleSignIn.setOnClickListener(this);

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
        else if (view == btnListView)
        {
            msg = "Clicou btnSaveToFirebase";
            this.startMyActivity( msg, ListViewActivity.class );

        }
        else if (view == btnImageUpload)
        {
            msg = "Clicou btnImageUpload";
            this.startMyActivity( msg, ImageUploadActivity.class );

        }
        else if (view == btnImageUploadShow)
        {
            msg = "Clicou btnSaveToFirebase";
            this.startMyActivity( msg, UploadDataActivity.class );

        }
        else if (view == btnAudeoRecorder)
        {
            msg = "Clicou btnImageUploadShow";
            this.startMyActivity( msg, AudeoRecorderActivity.class );

        }
        else if (view == btnGoogleSignIn)
        {
            msg = "Clicou btnImageUploadShow";
            this.startMyActivity( msg, GoogleSignInActivity.class );

        }
        else if (view == btnOfflineCapable)
        {
            msg = "Clicou btnOfflineCapable";
            this.startMyActivity( msg, AudeoRecorderActivity.class );

        }


    }

    private void startMyActivity(String msg, Class<?> clas)
    {
        Intent intent = new Intent(this, clas);
        startActivity( intent );

//        Toast.makeText(this, msg , Toast.LENGTH_LONG).show();
    }

}
