package com.sungkyul.aa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeProcess {

    public static String timePro(final String begin_time, final String end_time, String dateFormatStr) throws Exception
    {
        if( dateFormatStr == null ) dateFormatStr = "yyyy/MM/dd HH:mm:ss" ;
        Calendar calendar = Calendar.getInstance();
        DateFormat stringFormat = new SimpleDateFormat(dateFormatStr);

        long beginTime = 0;

        try {
            // String을 Date로 포맷
            Date beginDate = stringFormat.parse(begin_time);
            Date endDate = stringFormat.parse(end_time);

            long gap = (endDate.getTime() - beginDate.getTime()) / 1000; // 초 단위

            long hourGap = gap / 60 / 60;
            long reminder = ((long) (gap / 60)) % 60;
            long minuteGap = reminder;
            long secondGap = gap % 60;

            if (hourGap > 99) {
                hourGap = (long) 99;
            }


            String returnTime = hourGap
                    + ":" + minuteGap
                    + ":" + secondGap;
            
            return returnTime;
        }catch (ParseException e){
            e.printStackTrace();
            throw new Exception(e);
        }

    }

}
