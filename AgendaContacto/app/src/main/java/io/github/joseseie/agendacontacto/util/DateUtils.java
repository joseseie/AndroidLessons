package io.github.joseseie.agendacontacto.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jose Seie on 1/3/2018.
 */

public class DateUtils {

    public static Date getDate(int year, int monthOfYear, int dayOfMonth)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,monthOfYear,dayOfMonth);
        Date date = calendar.getTime();

        return date;
    }

    public static String dateToString(int year, int monthOfYear, int dayOfMonth)
    {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

        return format.format( getDate(year,monthOfYear,dayOfMonth) );
    }

    public static String dateToString(Date date)
    {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

        return format.format( date );
    }

}
