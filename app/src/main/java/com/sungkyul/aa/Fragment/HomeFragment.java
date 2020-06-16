package com.sungkyul.aa.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.aa.AddWorkActivity;
import com.sungkyul.aa.BackPressHandler;
import com.sungkyul.aa.R;
import com.sungkyul.aa.myDBHelper;
import com.sungkyul.aa.timeProcess;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    GridView gridView;
    int index;
    //이미지 배열 선언
    ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();
    //텍스트 배열 선언
    ArrayList<String> textArr = new ArrayList<String>();
    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;
    public static timeProcess timePro;

    String startTime, endTime, timeGap;
    Integer[] posterID = { R.drawable.icon01, R.drawable.icon02, R.drawable.icon03, R.drawable.icon04, R.drawable.icon05, R.drawable.icon06,
            R.drawable.icon07, R.drawable.icon08, R.drawable.icon09, R.drawable.icon10, R.drawable.icon11, R.drawable.icon12  };

    String[] posterText = { "수면", "이동",
            "식사", "운동","일", "쇼핑","여가 활동", "집안일",
            "영화", "걷기","공부", "인터넷",
          };


    Chronometer chrono;
    View view;
    Button btnStop;
    ImageView imgMain;
    TextView txtTitle, txtSubTitle;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, null);

        // 처음에는 보이지 않게 설정
        chrono = (Chronometer) view.findViewById(R.id.chronometer1);
        chrono.setVisibility(View.INVISIBLE);


        imgMain = (ImageView)view.findViewById(R.id.mainImage);
        txtSubTitle = (TextView)view.findViewById(R.id.mainSubTitle);
        txtTitle = (TextView)view.findViewById(R.id.mainTitle);

        // 버튼이 처음에는 보이지 않게 설정
        btnStop = (Button)view.findViewById(R.id.btnStop);
        btnStop.setVisibility(View.INVISIBLE);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 현재시간을 msec 으로 구한다.
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // nowDate 변수에 값을 저장한다.
                String endTime = sdfNow.format(date);

                myDBHelper = new myDBHelper(getActivity());
                db = myDBHelper.getWritableDatabase();
                try {
                    // 호출 대상 매서드
                    timeGap = timeProcess.timePro(startTime, endTime, null);
                } catch (Exception e) {
                    // 예외처리
                }

                myDBHelper.insert(db , posterText[index], startTime, endTime, timeGap);



                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("활동 종료").setMessage("활동을 종료하시겠습니까??");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        chrono.setVisibility(View.INVISIBLE);
                        btnStop.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), "활동이 종료되었습니다.", Toast.LENGTH_LONG).show();
                        imgMain.setImageResource(R.drawable.no);
                        txtTitle.setText("하는게 없습니다...");
                        txtSubTitle.setText(" ");

                        chrono.setBase(SystemClock.elapsedRealtime());
                        chrono.stop();
                        chrono.setTextColor(Color.BLUE);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        FloatingActionButton btnAddActivity = (FloatingActionButton) view.findViewById(R.id.btnAddActivity);
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddWorkActivity.class);
                startActivity(intent);
            }
        });


        for(int i=0; i<12; i++){
            picArr.add(BitmapFactory.decodeResource(getResources(), posterID[i]));
        }


        for (int i = 0 ; i < 12 ; i++) {
            textArr.add(posterText[i]);
        }


        gridView = (GridView) view.findViewById(R.id.gridView1);
        gridView.setAdapter(new gridAdapter());


        return view;
    }

    public class gridAdapter extends BaseAdapter {

        LayoutInflater inflater;


        public gridAdapter() {
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("활동 시작").setMessage("활동을 시작하시겠습니까??");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id)
                        {
                            Toast.makeText(getActivity(), "활동이 시작되었습니다.", Toast.LENGTH_LONG).show();
                            chrono.setVisibility(View.VISIBLE);
                            btnStop.setVisibility(View.VISIBLE);
                            imgMain.setImageResource(posterID[position]);
                            txtTitle.setText(posterText[position] + "중 입니다.");
                            // DB값 저장하기위한 용도
                            index = position;
                            txtSubTitle.setText("새로운 일정을 시작하셨습니다.");

                            // 현재시간을 msec 으로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재시간을 date 변수에 저장한다.
                            Date date = new Date(now);
                            // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // nowDate 변수에 값을 저장한다.
                            startTime = sdfNow.format(date);

                            chrono.setBase(SystemClock.elapsedRealtime());
                            chrono.start();
                            chrono.setTextColor(Color.RED);

                            chrono.setBase(SystemClock.elapsedRealtime());
                            chrono.start();
                            chrono.setTextColor(Color.RED);
                        }
                    });


                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id)
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });

            chrono.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                }
            });





            return convertView;

        }
    }



}