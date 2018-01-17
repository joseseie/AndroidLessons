package io.github.joseseie.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView    = (ListView) findViewById(R.id.listView);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-59e43.firebaseio.com/");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                ListViewActivity.this,
                android.R.layout.simple_list_item_1,
                databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

            }
        };

    }
}
