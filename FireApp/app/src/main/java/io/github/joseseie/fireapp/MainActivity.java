package io.github.joseseie.fireapp;

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

public class MainActivity extends AppCompatActivity {

    private EditText mVaueField, mKeyValue;
    private TextView mValueView;
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

        mValueView = (TextView) findViewById(R.id.mValueView);
        mKeyValue  = (EditText) findViewById(R.id.keyValue);
        mVaueField = (EditText) findViewById(R.id.valueField);
        btnSendData = (Button) findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = mVaueField.getText().toString();
                String key = mKeyValue.getText().toString();

                databaseReference
//                        .child(Helper.TABLE_USER) //Table name
                        .child( key ) //Key name
//                        .child(Helper.USER_profession) //Column(s)
                        .setValue( value ); //Val

                //Datasnipshoot
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        StringBuilder value = new StringBuilder();

                        Log.v("E_VALUE","Data:" + dataSnapshot.getValue());

                        for (DataSnapshot objSnap : dataSnapshot.getChildren())
                        {
                            value.append(objSnap.getValue( String.class ));
                            value.append("\n");
                        }


                        mValueView.setText( value.toString() );

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Toast.makeText(MainActivity.this,"Child added",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        Toast.makeText(MainActivity.this,"Child Charged",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                        Toast.makeText(MainActivity.this,"Child Removed",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
