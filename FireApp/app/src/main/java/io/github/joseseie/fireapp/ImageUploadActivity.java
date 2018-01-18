package io.github.joseseie.fireapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ImageUploadActivity extends AppCompatActivity {

    private Button btnImageUpload;

    private StorageReference mStorage;

    private static final int GALLERY_INTENT = 2;

    private ProgressDialog mProgressDialgod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        btnImageUpload = (Button) findViewById(R.id.btnImageUpload);

        mStorage = FirebaseStorage.getInstance().getReference();

        mProgressDialgod = new ProgressDialog(this);
        btnImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");

                startActivityForResult(intent, GALLERY_INTENT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT &&  resultCode == RESULT_OK)
        {
            mProgressDialgod.setMessage("Uploading...");
            mProgressDialgod.show();

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile( uri ).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(ImageUploadActivity.this,"Upload Done", Toast.LENGTH_LONG).show();
                    mProgressDialgod.dismiss();
                }
            });

        }


    }
}
