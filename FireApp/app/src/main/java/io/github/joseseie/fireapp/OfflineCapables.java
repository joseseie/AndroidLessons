package io.github.joseseie.fireapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jose Seie on 1/18/2018.
 */

public class OfflineCapables extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
