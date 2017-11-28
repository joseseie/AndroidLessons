package io.github.joseseie.aula02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main extends Activity {

    protected void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.main);
    }

    public static void cliquei(View v) {

        Log.i("Ola","Eu passei por aqui.");

    }



}