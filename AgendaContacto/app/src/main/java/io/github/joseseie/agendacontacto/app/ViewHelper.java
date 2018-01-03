package io.github.joseseie.agendacontacto.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Jose Seie on 1/3/2018.
 */

public class ViewHelper {

    public static ArrayAdapter<String> createArrayAdapter(Context context, Spinner spinner)
    {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter( arrayAdapter );

        return arrayAdapter;
    }
}
