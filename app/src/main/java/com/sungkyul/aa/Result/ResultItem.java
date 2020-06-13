package com.sungkyul.aa.Result;

import androidx.annotation.NonNull;

public class ResultItem {
    //결과에들어가는 객체를 생성하기위해 만든 클래스 WY
    String name;
    String time;
    int resid;

    public ResultItem(String name,String time, int resid){
        this.name = name;
        this.time = time;
        this.resid = resid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    @NonNull
    @Override
    public String toString() {
        return "ResultItem{" + "name = " + name + "time = " + time + "resid = " + resid;
    }
}
