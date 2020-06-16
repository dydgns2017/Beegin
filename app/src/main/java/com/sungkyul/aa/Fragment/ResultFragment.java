package com.sungkyul.aa.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sungkyul.aa.R;
import com.sungkyul.aa.Result.ResultItem;
import com.sungkyul.aa.Result.ResultItemView;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    //날짜txt값
    TextView txtDate;
    //이미지 버튼 날짜 +,-
    ImageView ImgDatePlus, ImgDateMinus;
    //날짜선택 데이트 피커
    DatePicker Dpicker;
    final Calendar c = Calendar.getInstance();
    //ListView
    ListView listresult;
    //PieChart
    PieChart pieChart;
    //funChart
    LinearLayout Linerfun;

    //날짜값
    int dyear = c.get(Calendar.YEAR);
    int dmonth = c.get(Calendar.MONTH)+1;
    int ddayofmonth = c.get(Calendar.DAY_OF_MONTH);



    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_result, container, false);
        //메뉴
        setHasOptionsMenu(true);
        //id값 가져오기
        txtDate = (TextView)rootview.findViewById(R.id.txtDate);
        ImgDatePlus = (ImageView)rootview.findViewById(R.id.ImgDatePlus);
        ImgDateMinus = (ImageView)rootview.findViewById(R.id.ImgDateMinus);
        Dpicker = (DatePicker)rootview.findViewById(R.id.DPicker);
        listresult = (ListView)rootview.findViewById(R.id.listresult);
        pieChart = (PieChart)rootview.findViewById(R.id.pieChart);
        Linerfun = (LinearLayout)rootview.findViewById(R.id.Linerfun);

        ///////////////////////Start_PIECHART//////////////////////////////////////////////////
        pieChart.setUsePercentValues(true); //퍼센트를 사용하겠다.
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,10,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(20f,"청소"));
        yValues.add(new PieEntry(90f,"공부"));
        yValues.add(new PieEntry(30f,"식사"));
        yValues.add(new PieEntry(90f,"운동"));
        yValues.add(new PieEntry(30f,"휴식"));

        //파이차트의 라벨
        Description description = new Description();
        description.setText("원형 차트"); //라벨
        description.setTextSize(15);
        description.setPosition(870,100);
        pieChart.setDescription(description);

//        pieChart.animateY(1000, Easing.EasingFunction.class.cast(2));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
//        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);

        ///////////////////////END_PIECHART//////////////////////////////////////////////////


        //리스트뷰 연결
        ResultAdapter adapter = new ResultAdapter();
        adapter.addItem(new ResultItem("청소", "07:30", "07:50", "50분", R.drawable.mov01));
        adapter.addItem(new ResultItem("공부", "08:30", "10:00", "90분", R.drawable.mov02));
        adapter.addItem(new ResultItem("식사", "10:10", "10:40", "30분", R.drawable.mov03));
        adapter.addItem(new ResultItem("운동", "11:00", "12:30", "90분", R.drawable.mov04));
        adapter.addItem(new ResultItem("휴식", "12:30", "13:00", "30분", R.drawable.mov05));
        //어댑터 연결
        listresult.setAdapter(adapter);


        //현재날짜로 설정하기
        txtDate.setText(dyear + "-" + dmonth + "-" + ddayofmonth);

        //날짜클릭했을때
        txtDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //리스트뷰가 보여지고있으면 끄겠다.
                if(listresult.getVisibility() == View.VISIBLE){
                    listresult.setVisibility(View.INVISIBLE);
                }
                //파이차트가 보여지고있으면 끄겠다.
                if(pieChart.getVisibility() == View.VISIBLE){
                    pieChart.setVisibility(View.INVISIBLE);
                }
                //펀차트가 보여지고있으면 끄겠다.
                if(Linerfun.getVisibility() == View.VISIBLE){
                    Linerfun.setVisibility(View.INVISIBLE);
                }
                //txtDate가 켜져있으면 리스트뷰보여주소 txtDate는 끄겠다.
                if(Dpicker.getVisibility() == View.VISIBLE){
                    Dpicker.setVisibility(View.INVISIBLE);
                    listresult.setVisibility(View.VISIBLE);
                }else{ //txtDate가 꺼져있으면 txtDate를 비지블
                    Dpicker.setVisibility(View.VISIBLE);
                }
            }
        });

        Dpicker.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.i("Test", "선택년도 : " +year + " 월 : " + (month+1) + " 일 : " + dayOfMonth);
                txtDate.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                c.set(year, month, dayOfMonth);

//                dyear = year;
//                dmonth = month+1;
//                ddayofmonth = dayOfMonth;

                Dpicker.setVisibility(View.INVISIBLE);
                listresult.setVisibility(View.VISIBLE);
            }
        });

        ImgDatePlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //일에 +1
                c.add(Calendar.DATE, 1);
                dyear = c.get(Calendar.YEAR);
                dmonth = c.get(Calendar.MONTH)+1;
                ddayofmonth = c.get(Calendar.DAY_OF_MONTH);
                txtDate.setText(dyear + "-" + (dmonth) + "-" + ddayofmonth);
            }
        });

        ImgDateMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                c.add(Calendar.DATE, -1);
                dyear = c.get(Calendar.YEAR);
                dmonth = c.get(Calendar.MONTH)+1;
                ddayofmonth = c.get(Calendar.DAY_OF_MONTH);
                txtDate.setText(dyear + "-" + (dmonth) + "-" + ddayofmonth);
            }
        });

        // Inflate the layout for this fragment
        return rootview;
    }


    //메뉴바 만들기
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_result,menu);

    }
    //메뉴액션


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curid = item.getItemId();

        switch (curid){
            case R.id.nav_textchart:
                //텍스트로 보기 클릭시
                Log.i(this.getClass().getName(), "텍스트로 보기 클릭");
                if(Dpicker.getVisibility() == View.VISIBLE){
                    Dpicker.setVisibility(View.INVISIBLE);
                }
                if(pieChart.getVisibility() == View.VISIBLE){
                    pieChart.setVisibility(View.INVISIBLE);
                }
                if(Linerfun.getVisibility() == View.VISIBLE){
                    Linerfun.setVisibility(View.INVISIBLE);
                }
                listresult.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_criclegraph:
                Log.i(this.getClass().getName(), "원형그래프로보기 클릭");
                if(Dpicker.getVisibility() == View.VISIBLE){
                    Dpicker.setVisibility(View.INVISIBLE);
                }
                if(listresult.getVisibility() == View.VISIBLE){
                    listresult.setVisibility(View.INVISIBLE);
                }
                if(Linerfun.getVisibility() == View.VISIBLE){
                    Linerfun.setVisibility(View.INVISIBLE);
                }
                pieChart.setVisibility(View.VISIBLE);

                break;

            case R.id.nav_fungraph:
                Log.i(this.getClass().getName(), "재밌게 클릭");
                if(Dpicker.getVisibility() == View.VISIBLE){
                    Dpicker.setVisibility(View.INVISIBLE);
                }
                if(pieChart.getVisibility() == View.VISIBLE){
                    pieChart.setVisibility(View.INVISIBLE);
                }
                if(listresult.getVisibility() == View.VISIBLE){
                    listresult.setVisibility(View.INVISIBLE);
                }
                Linerfun.setVisibility(View.VISIBLE);

                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //리스트뷰 어댑터
    class ResultAdapter extends BaseAdapter{
        ArrayList<ResultItem> items = new ArrayList<ResultItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ResultItem item){
            items.add(item);
        }


        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        //어댑터가 데이터를 관리하고 뷰도 만듬
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ResultItemView resultItemView = null;

            if(convertView == null){
                resultItemView = new ResultItemView(getActivity());
            }else{
                resultItemView = (ResultItemView)convertView;
            }

            ResultItem item = items.get(position);
            resultItemView.setName(item.getName());
            resultItemView.setstartTime(item.getStarttime());
            resultItemView.setendTime(item.getEndtime());
            resultItemView.setTime(item.getTime());
            resultItemView.setImgae(item.getResid());


            return resultItemView;
        }
    }



}