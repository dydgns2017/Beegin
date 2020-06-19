package com.sungkyul.aa.Board;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sungkyul.aa.R;

public class BoardItemView extends LinearLayout {
    //언제어디든지 사용하기 위해
    TextView txt_board_title, txt_board_commentCount, txt_board_userid, txt_board_date;


    public BoardItemView(Context context){
        super(context);
        init(context);
    }

    public BoardItemView(Context context, AttributeSet attrs) {super(context, attrs);}


    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.board_list, this, true);

        txt_board_title = findViewById(R.id.txt_board_title);
        txt_board_commentCount = findViewById(R.id.txt_board_commentCount);
        txt_board_userid = findViewById(R.id.txt_board_userid);
        txt_board_date = findViewById(R.id.txt_board_date);
    }

    public void setTitle(String title){txt_board_title.setText(title);}

    public void setCommentCount(String count){txt_board_commentCount.setText(count);}

    public void setUserId(String userid){txt_board_userid.setText(userid);}

    public void setDate(String date){txt_board_date.setText(date);}
}
