package com.sungkyul.aa.planFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sungkyul.aa.MainActivity;
import com.sungkyul.aa.R;
import com.sungkyul.aa.myDBHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddPlanActivity extends Activity {


    RadioButton rdoActivity, rdoTime;
    GridView plan_grid;

    CalendarView calView;
    TimePicker timePicker;
    Button btnaddPlan;
    EditText edt_add_name;
    ImageView mainImage;

    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;

    //이미지 배열 선언
    ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();
    //텍스트 배열 선언
    ArrayList<String> textArr = new ArrayList<String>();

    // 최대30개
    Integer posterID[] = new Integer[30];
    String posterText[] = new String[30];

    int index;

    // 현재 데이터 개수 -1
    int i=0;
    int length;

    String nHourDay;
    String nMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplan);


        mainImage = (ImageView)findViewById(R.id.mainImage);
        myDBHelper = new myDBHelper(this);
        db = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT activityname, img_src FROM user_activity;", null);

        while(cursor.moveToNext()){
            posterText[i] = cursor.getString(0);
            posterID[i] = cursor.getInt(1);
            Log.i(this.getClass().getName(),"이게 제일 중요!! -> "+ posterID[i] + posterText[i] + "-->" + i);
            i++;
            length = i;
        }

        rdoActivity = (RadioButton)findViewById(R.id.rdoActivity);
        rdoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clicked activity button", Toast.LENGTH_LONG).show();
                timePicker.setVisibility(View.GONE);
                plan_grid.setVisibility(View.VISIBLE);
            }
        });
        rdoTime = (RadioButton)findViewById(R.id.rdoTime);
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clicked time button", Toast.LENGTH_LONG).show();
                timePicker.setVisibility(View.VISIBLE);
                plan_grid.setVisibility(View.GONE);
            }
        });
        plan_grid = (GridView)findViewById(R.id.plan_grid);
        timePicker = (TimePicker)findViewById(R.id.timepicker1);

        timePicker.setIs24HourView(true);
        edt_add_name = (EditText)findViewById(R.id.edt_plan_add_name);

        timePicker.setVisibility(View.INVISIBLE);
        plan_grid.setVisibility(View.INVISIBLE);

        btnaddPlan = (Button)findViewById(R.id.btnAddAddPlan);
        btnaddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //////////////////////////////////////////////////////////////////////// insert 문 추가할 것.
                myDBHelper = new myDBHelper(getApplicationContext());
                db = myDBHelper.getWritableDatabase();

                // 현재시간을 msec 으로 구한다.
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // nowDate 변수에 값을 저장한다.
                String currentTime = sdfNow.format(date);


                Log.i(this.getClass().getName(),"플랜 입력값 -> "+ "" + edt_add_name.getText().toString()
                        + "" + posterText[index] + "--" +  posterID[index] + "--" +
                        ((timePicker.getCurrentHour()*60) + timePicker.getCurrentMinute()) + "startTime ==>" + currentTime);

                myDBHelper.plan_insert(db, edt_add_name.getText().toString(), posterText[index], posterID[index],
                        ""+((timePicker.getCurrentHour()*60) + timePicker.getCurrentMinute()) , currentTime);


                startActivity(intent);
                finish();
            }
        });

        for(int i=0; i<length; i++){
            picArr.add(BitmapFactory.decodeResource(getResources(), posterID[i]));
        }


        for (int i = 0 ; i<length ; i++) {
            textArr.add(posterText[i]);
        }


        plan_grid.setAdapter(new gridAdapter());
    }

    public class gridAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public gridAdapter() {
            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return picArr.size();    //그리드뷰에 출력할 목록 수
        }

        @Override
        public Object getItem(int position) {
            return picArr.get(position);    //아이템을 호출할 때 사용하는 메소드
        }

        @Override
        public long getItemId(int position) {
            return position;    //아이템의 아이디를 구할 때 사용하는 메소드
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.gridview_list, parent, false);

            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.img_grid);
            TextView textView = (TextView) convertView.findViewById(R.id.txt_grid);

            imageView.setImageBitmap(picArr.get(position));
            textView.setText(textArr.get(position));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_LONG).show();
                    mainImage.setImageResource(posterID[position]);
                    index = position;


                        }
                    });


            return convertView;

        }
}


}
