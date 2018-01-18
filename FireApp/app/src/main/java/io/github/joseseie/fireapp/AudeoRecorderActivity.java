package io.github.joseseie.fireapp;

import android.app.ProgressDialog;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class AudeoRecorderActivity extends AppCompatActivity {

    private Button mRecordBtn;
    private TextView mRecordLabel;

    private MediaRecorder mRecorder;
    private String mFileName = null;

    private static final String LOG_TAG = "Record_log";
    private ProgressDialog progressDialog;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audeo_recorder);

        mRecordBtn  = (Button) findViewById(R.id.btnRecordAudeo);
        mRecordLabel = (TextView) findViewById(R.id.recordLabel);

        mStorage = FirebaseStorage.getInstance().getReference();

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/record_audio.3gp";

        progressDialog = new ProgressDialog(this);

        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    startRecording();
                    mRecordLabel.setText("Recording started...");
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    stopRecording();
                    mRecordLabel.setText("Recording stoped...");
                }

                return false;
            }
        });

    }

    private void startRecording()
    {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try
        {
            mRecorder.prepare();
        } catch (IOException e)
        {
            Log.e(LOG_TAG, "Prepare() failed");
        }
        mRecorder.start();
    }

    private void stopRecording()
    {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

        uploadAudeo();
    }

    private void uploadAudeo() {

        progressDialog.setMessage("Uploading Audio . . .");
        progressDialog.show();

        StorageReference filePath = mStorage.child("Audio").child("new_audio.3gp");

        Uri uri = Uri.fromFile( new File( mFileName ));

        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();

                mRecordLabel.setText("Upload Finished");
            }
        });

    }


}
