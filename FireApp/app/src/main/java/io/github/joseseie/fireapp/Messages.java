package io.github.joseseie.fireapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jose Seie on 1/17/2018.
 */

public class Messages {

    public static void toast(Context context, String msg)
    {
        Toast.makeText(context, "Login" , Toast.LENGTH_LONG).show();
    }

}
