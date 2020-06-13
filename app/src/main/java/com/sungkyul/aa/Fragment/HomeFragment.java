package com.sungkyul.aa.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.sungkyul.aa.AddWorkActivity;
import com.sungkyul.aa.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

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
/*                // 현재시간을 msec 으로 구한다.
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // nowDate 변수에 값을 저장한다.
                String formatDate = sdfNow.format(date);
                dateEnd = (TextView) findViewById(R.id.dateEnd);
                dateEnd.setText(formatDate);    // TextView 에 현재 시간 문자열 할당  */

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
                        txtSubTitle.setText("에에 !! 여기까지 테스트를 해보셨다구요??");

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

        Button btnAddActivity = (Button) view.findViewById(R.id.btnAddActivity);
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddWorkActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        final GridView gv = (GridView) view.findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(getActivity());
        gv.setAdapter(gAdapter);
        return view;
    }


    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        Integer[] posterID = { R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
                R.drawable.mov09, R.drawable.mov10, R.drawable.mov01,
                R.drawable.mov02, R.drawable.mov03, R.drawable.mov04,
                R.drawable.mov05, R.drawable.mov06, R.drawable.mov07,
                R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,
                R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09,
                R.drawable.mov10 };

        String[] posterText = { "써니 보자", "완득이 보자",
                "괴물 보자", "라디오스타 보자","비열한거리 보자", "왕의남자 보자","아일랜드 보자", "웰컴투 동막골 보자",
                "헬보이 보자", "박원용 보자","써니 보자", "완득이 보자",
                "괴물 보자", "라디오스타 보자","비열한거리 보자", "왕의남자 보자","아일랜드 보자", "웰컴투 동막골 보자",
                "헬보이 보자", "박원용 보자","써니 보자", "완득이 보자",
                "괴물 보자", "라디오스타 보자","비열한거리 보자", "왕의남자 보자","아일랜드 보자", "웰컴투 동막골 보자",
                "헬보이 보자", "박원용 보자"};

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterID[position]);


            final int pos = position;



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
                            txtTitle.setText(posterText[position]);
                            txtSubTitle.setText("새로운 일정을 시작하셨습니다!!@@");

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
/*                    // 현재시간을 msec 으로 구한다.
                    long now = System.currentTimeMillis();
                    // 현재시간을 date 변수에 저장한다.
                    Date date = new Date(now);
                    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    // nowDate 변수에 값을 저장한다.
                    String formatDate = sdfNow.format(date);
                    txtTime = (TextView) view.findViewById(R.id.txt);
                    dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당  */
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    chrono.setTextColor(Color.RED);

                }
            });
            return imageView;
        }
    }
}
