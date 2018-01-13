package io.github.joseseie.myfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.joseseie.myfirebase.modelo.Pessoa;

public class MainActivity extends AppCompatActivity {

    EditText  edtNome, edtEmail;
    ListView listV_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Pessoa> listaPessoa = new ArrayList<>();
    private ArrayAdapter<Pessoa> arrayAdapterPessoa;

    Pessoa pessoaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail    = (EditText) findViewById(R.id.editEmail);
        edtNome     = (EditText) findViewById(R.id.editNome);
        listV_dados = (ListView) findViewById(R.id.listV_dados);

        inicializaFirebase();

        eventoDatabase();

        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pessoaSelecionada = (Pessoa) adapterView.getItemAtPosition(i);
                edtNome.setText( pessoaSelecionada.getNome() );
                edtEmail.setText( pessoaSelecionada.getEmail() );

            }
        });
    }

    private void eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaPessoa.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren())
                {
                    Pessoa p = objSnapshot.getValue(Pessoa.class);
                    listaPessoa.add( p );
                }
                arrayAdapterPessoa = new ArrayAdapter<Pessoa>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        listaPessoa);
                listV_dados.setAdapter( arrayAdapterPessoa );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializaFirebase() {

        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled( true );
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_novo)
        {
            Pessoa p = new Pessoa();
            p.setUid(UUID.randomUUID().toString() );
            p.setNome( edtNome.getText().toString());
            p.setEmail( edtEmail.getText().toString() );
            databaseReference.child("Pessoa").child( p.getUid() ).setValue( p );
            limparCampos();

        }
        else if(id == R.id.menu_actualizar && pessoaSelecionada.getUid() != null)
        {
            Pessoa p = new Pessoa();
            p.setUid( pessoaSelecionada.getUid() );
            p.setNome( edtNome.getText().toString());
            p.setEmail( edtEmail.getText().toString() );
            databaseReference.child("Pessoa").child( p.getUid() ).setValue( p );
            limparCampos();
        }
        else if(id == R.id.menu_deletar)
        {
            Pessoa p = new Pessoa();
            p.setUid( pessoaSelecionada.getUid() );
            databaseReference.child("Pessoa").child(p.getUid()).removeValue();
            limparCampos();
        }
        return true;
    }

    private void limparCampos() {
        edtEmail.setText("");
        edtNome.setText("");
    }
}
