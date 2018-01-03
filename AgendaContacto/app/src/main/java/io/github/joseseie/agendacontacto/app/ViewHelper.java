package io.github.joseseie.agendacontacto.app;

import android.widget.ArrayAdapter;

/**
 * Created by Jose Seie on 1/3/2018.
 */

public class ViewHelper {

    public static ArrayAdapter<String> createArrayAdapter()
    {
        adpTipoEmail = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }
}
