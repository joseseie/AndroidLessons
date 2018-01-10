package util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Jose Seie on 1/10/2018.
 */

public class Mensagem {

    public static void Msg(Activity activity, String msg)
    {
        Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
    }



}
