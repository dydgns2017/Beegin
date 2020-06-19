package com.sungkyul.aa.planFragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sungkyul.aa.myDBHelper;


public class TimeProcesser {

    // plan에서 시간을 처리하는 클래스 입니다.
    // 년/ 월/ 일  시:분:초
    int[] Date = new int[6];

    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;

    public String Timeprcess(Context context, String activityName, String date){

        int minuteTime = 0;
        String ReturnMinuteTime;
        String[] D = date.split(" " );
        String[] year = D[0].split("/");
        String[] Time = D[1].split(":");

        for(int i = 0; i<Date.length; i++){

            if(i<3) {
                Date[i] = Integer.parseInt(year[i]);
            }else {
                Date[i] = Integer.parseInt(Time[i-3]);
            }
        }

        for(int i = 0; i<Date.length; i++) {
            Log.i(this.getClass().getName(), "DATE " + i + " ==> " + Date[i]);
        }


        myDBHelper = new myDBHelper(context);
        db = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT timestart,timedata FROM time_db WHERE activityname = + '" + activityName + "' ;", null);

        while(cursor.moveToNext()){

            String timestart = (cursor.getString(0));

            int compare = date.compareTo( timestart );
            if(compare < 0) {
                String timeStr[] = (cursor.getString(1)).split(":");
                minuteTime += (Integer.parseInt(timeStr[0]) * 60) + Integer.parseInt(timeStr[1]);
            }
        }
        return minuteTime+"";
        // 분의 형태로 Return 해야함.
    }
}