package io.github.joseseie.agendacontacto;

/**
 * Created by Jose Seie on 12/31/2017.
 */

import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context)
    {
        super(context,"AGENDA",null,1);
    }
    public void onCreate(SQLiteDatabase db){

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
