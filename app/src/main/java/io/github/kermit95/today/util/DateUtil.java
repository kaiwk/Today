package io.github.kermit95.today.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by kermit on 16/3/19.
 */
public class DateUtil {

    private static final String TAG = "DateUtil";

    public static String getWeekDay(String dateString, String formatString){
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK)-1;
        String[] week = {"Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat"};
        return week[index];
    }

}
