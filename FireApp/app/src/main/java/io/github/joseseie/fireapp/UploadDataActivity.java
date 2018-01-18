package io.github.joseseie.fireapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadDataActivity extends AppCompatActivity {

    private Button mUploadBtn;
    private ImageView mImageView;

    private StorageReference mStorage;

    private static final int CAMERA_REQUEST_CODE = 1;

    private ProgressDialog mProgressDialgod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_upload);

        mUploadBtn = (Button) findViewById(R.id.btnDataUpload);
        mImageView      = (ImageView) findViewById(R.id.imageView);

        mStorage = FirebaseStorage.getInstance().getReference();

        mProgressDialgod = new ProgressDialog(this);

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, CAMERA_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE &&  resultCode == RESULT_OK)
        {
            mProgressDialgod.setMessage("Uploading...");
            mProgressDialgod.show();

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile( uri ).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressDialgod.dismiss();

                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    Picasso.with(UploadDataActivity.this).load( downloadUrl ).fit().centerCrop().into( mImageView );

                    Toast.makeText(UploadDataActivity.this,"Upload Done", Toast.LENGTH_LONG).show();

                }
            });

        }
        else
        {
            Messages.toast(this,"Nao foi possivel carregar a imagem");
        }


    }
}
