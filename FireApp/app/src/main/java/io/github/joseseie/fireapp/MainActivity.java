package io.github.joseseie.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText mVaueField, mKeyValue;
    private Button btnSendData;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference  databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        mKeyValue  = (EditText) findViewById(R.id.keyValue);
        mVaueField = (EditText) findViewById(R.id.valueField);
        btnSendData = (Button) findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = mVaueField.getText().toString();
                String key = mKeyValue.getText().toString();

                databaseReference
                        .child(Helper.TABLE_USER) //Table name
                        .child( key ) //Key name
                        .child(Helper.USER_profession) //Column(s)
                        .setValue( value ); //Val

            }
        });

    }
}
