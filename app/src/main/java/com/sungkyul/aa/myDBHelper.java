package com.sungkyul.aa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDBHelper extends SQLiteOpenHelper {

    public myDBHelper(Context context){
        super(context, "Time", null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // time_db 생성
        db.execSQL("CREATE TABLE time_db ( " +
                "pk INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "activityname TEXT NOT NULL, " +
                "timestart TEXT, " +
                "timeend TEXT, " +
                "timedata TEXT );");
    }

    public void insert(SQLiteDatabase db, String activityName, String timeStart, String timeEnd, String timeGap){
        Log.i(this.getClass().getName(),activityName + timeEnd +timeStart + " => " + timeGap);
        db.execSQL("INSERT INTO time_db(activityname, timestart, timeend, timedata) VALUES ('" + activityName + "', '" + timeStart
                + "', '" + timeEnd + "', '" + timeGap + "');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 버전이 업그레이드되면 기존꺼 삭제하구 db새로 생성
        db.execSQL("DROP TABLE IF EXISTS time_db");
        onCreate(db);
    }
}
