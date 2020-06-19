package com.sungkyul.aa.planFragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sungkyul.aa.myDBHelper;


public class TimeProcesser {

    // // 생성 이후 시간 데이터만 더해주겠다... 요런뜻.
    // 년/ 월/ 일  시:분:초
    int[] Date = new int[6];

    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;

    public String Timeprcess(Context context, String activityName, String date){

        int minuteTime = 0;

        myDBHelper = new myDBHelper(context);
        db = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT timestart,timedata FROM time_db WHERE activityname = + '" + activityName + "' ;", null);

        while(cursor.moveToNext()){

            String timestart = (cursor.getString(0));

            // 생성 이후 시간 데이터만 더해주겠다... 요런뜻.
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