package com.sungkyul.aa.Result;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sungkyul.aa.R;

public class ResultItemView extends LinearLayout {

    //언제 어디서든 사용하게 하기위해
    TextView txt_result_name, txt_result_time;
    TextView txt_result_starttime, txt_result_endtime;

    ImageView Img_result_list;

    public ResultItemView(Context context) {
        super(context);
        init(context);//인플레이션해서 붙혀주는 역활이에욧
    }

    public ResultItemView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.result_item_list, this, true);

        txt_result_name = findViewById(R.id.txt_result_name);
        txt_result_time = findViewById(R.id.txt_result_time);
        Img_result_list = findViewById(R.id.Img_result_list);
        txt_result_starttime = findViewById(R.id.txt_result_starttime);
        txt_result_endtime = findViewById(R.id.txt_result_endtime);


    }

    public void setName(String name){
        txt_result_name.setText(name);
    }

    public void setTime(String time){
        txt_result_time.setText(time);
    }

    public void setstartTime(String time){
        txt_result_starttime.setText(time);
    }

    public void setendTime(String time){
        txt_result_endtime.setText(time);
    }



    public void setImgae(int resid){
        Img_result_list.setImageResource(resid);
    }



}