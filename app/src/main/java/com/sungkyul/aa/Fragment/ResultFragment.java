package com.sungkyul.aa.Fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import com.sungkyul.aa.Result.ResultActivity;
import com.sungkyul.aa.Result.ResultItem;
import com.sungkyul.aa.Result.ResultItemView;
import com.sungkyul.aa.myDBHelper;

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
    ImageView resultIMG;
    TextView txtDay, txtHour, txtMinute, txtWage, txtCurry, txtSing, txtPcRoom;
    //DB
    public static com.sungkyul.aa.myDBHelper myDBHelper;
    public static SQLiteDatabase db;

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

        resultIMG = (ImageView)rootview.findViewById(R.id.resultImg);
        txtDay = (TextView)rootview.findViewById(R.id.txtDay);
        txtHour = (TextView)rootview.findViewById(R.id.txtHour);
        txtMinute = (TextView)rootview.findViewById(R.id.txtMinute);
        txtWage = (TextView)rootview.findViewById(R.id.txtWage);
        txtCurry = (TextView)rootview.findViewById(R.id.txtCurry);
        txtSing = (TextView)rootview.findViewById(R.id.txtSing);
        txtPcRoom = (TextView)rootview.findViewById(R.id.txtPCRoom);


        //리스트뷰 연결
        final ResultAdapter adapter = new ResultAdapter();
        myDBHelper = new myDBHelper(getActivity());
        db = myDBHelper.getWritableDatabase();
        String selectAll = "Select * FROM time_db";
        String strdmonth10 = dmonth +"";
        if(Integer.parseInt(strdmonth10) < 10) strdmonth10 = "0"+strdmonth10;
        String strdayofMonth10 = ddayofmonth +"";
        if(Integer.parseInt(strdayofMonth10) < 10) strdayofMonth10 = "0"+strdayofMonth10;



        String timeHMS = dyear + "/" + strdmonth10 + "/" + strdayofMonth10 ;
        String selectDateAll = "Select * FROM time_db WHERE timestart LIKE '" + timeHMS + "%'" ;

        Log.i(this.getClass().getName(), "Select * FROM time_db WHERE timestart LIKE '" + timeHMS + "%'");
        Log.i(this.getClass().getName(), "dyear --> " + dyear);
        Log.i(this.getClass().getName(), "strdmonth10 --> " + strdmonth10);
        Log.i(this.getClass().getName(), "strddayofmonth --> " + strdayofMonth10);

        Cursor cursor = db.rawQuery(selectDateAll,null);
        int resource = 0;
        String getImgsrc = "";

        //아이템 객체 배열
        ResultItem[] resultItems = new ResultItem[cursor.getCount()];


        while(cursor.moveToNext()){
            Log.i(this.getClass().getName(), "Cursor안에들어옴!");
            String activityname = cursor.getString(1);
            String starttime = cursor.getString(2);
            starttime = starttime.split(" ")[1].split(":")[0] +
                        ":" + starttime.split(" ")[1].split(":")[1];
            String endtime = cursor.getString(3);
            endtime = endtime.split(" ")[1].split(":")[0] +
                    ":" + endtime.split(" ")[1].split(":")[1];
            String timedata = cursor.getString(4);
//            timedata = timedata.split(":")[0] + ":" + timedata.split(":")[1];
            String timeHour = timedata.split(":")[0];
            String timeMinute = timedata.split(":")[1];

            if(Integer.parseInt(timeHour) < 10) timeHour = "0" + timeHour;
            if(Integer.parseInt(timeMinute) < 10) timeMinute = "0" + timeMinute;
            timedata = timeHour + " : " + timeMinute;

            getImgsrc = "Select * FROM user_activity WHERE activityname = " + "'" +activityname +"'";
            Cursor cursor1 = db.rawQuery(getImgsrc,null);
            cursor1.moveToNext();
            resource = cursor1.getInt(2);


            // 파이차트를 쓰기위해
            resultItems[cursor.getPosition()] = new ResultItem(activityname, starttime, endtime, cursor.getString(4), resource);

            adapter.addItem(new ResultItem(activityname, starttime, endtime, timedata, resource));
        }
//        db.close();

//        adapter.addItem(new ResultItem("청소", "07:30", "07:50", "50분", R.drawable.mov01));
//        adapter.addItem(new ResultItem("공부", "08:30", "10:00", "90분", R.drawable.mov02));
//        adapter.addItem(new ResultItem("식사", "10:10", "10:40", "30분", R.drawable.mov03));
//        adapter.addItem(new ResultItem("운동", "11:00", "12:30", "90분", R.drawable.mov04));
//        adapter.addItem(new ResultItem("휴식", "12:30", "13:00", "30분", R.drawable.mov05));
        //어댑터 연결
        listresult.setAdapter(adapter);


        ///////////////////////Start_PIECHART//////////////////////////////////////////////////
        pieChart.setUsePercentValues(true); //퍼센트를 사용하겠다.
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,10,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        ArrayList<String> namearr = new ArrayList<>();
        for (int i = 0;i<resultItems.length;i++) {
            if(!namearr.contains(resultItems[i].getName())){
                namearr.add(resultItems[i].getName());
            }
        }
        Log.i(this.getClass().getName(), "namearr.size ==> " + namearr.size());


        //시간이랑 이름만있는거
        ResultActivity[] resultActivities = new ResultActivity[namearr.size()];
        for (int i =0; i<namearr.size(); i++) {
            resultActivities[i] = new ResultActivity(namearr.get(i),0);
            Log.i(this.getClass().getName(), "namearr.get[" + i + "] ==> " +namearr.get(i));
        }


        Log.i(this.getClass().getName(), "resultActivities.length() --> " + resultActivities.length);
        Log.i(this.getClass().getName(), "resultItems.length() -->  " + resultItems.length);

        for (int i = 0; i<resultActivities.length; i++){ //6
            for (int j = 0;j<resultItems.length; j++){ //10
                if(resultActivities[i].getName().equals(resultItems[j].getName())){
                    Log.i(this.getClass().getName(), "I -->" + i + "     j --> " + j);
                    Log.i(this.getClass().getName(), "resultActivity["+i+"].getname ==> " + resultActivities[i].getName());
                    Log.i(this.getClass().getName(), "resultItems["+j+"].getname ==> " + resultItems[j].getName());
                    resultActivities[i].PlusTime(resultItems[j].getSecond());
                }
            }

        }

        //yValues에 값추가해주기
        for (int i = 0;i<resultActivities.length;i++){
            yValues.add(new PieEntry(resultActivities[i].getTime() ,resultActivities[i].getName()));
        }

        //파이차트의 라벨
        Description description = new Description();
        description.setText("원형 차트"); //라벨
        description.setTextSize(15);
        description.setPosition(870,100);
        pieChart.setDescription(description);

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

                refresh();

                dyear = year;
                dmonth = month+1;
                ddayofmonth = dayOfMonth;

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
                refresh();
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
                refresh();
            }
        });

        //Funchart--Code

        final int[] imgResource = {R.drawable.icon01, R.drawable.icon02,  R.drawable.icon03, R.drawable.icon04, R.drawable.icon05, R.drawable.icon06, R.drawable.icon07,
                R.drawable.icon08, R.drawable.icon09, R.drawable.icon10, R.drawable.icon11, R.drawable.icon12};

        resultIMG.setImageResource(imgResource[8]);
        int img_src = imgResource[8];
        Log.i(this.getClass().getName(), "img_src --> " + img_src);

        //이미지의 맞는 이름을 같고오기 위한 쿼리


        String selectImgAll = "Select * FROM user_activity WHERE img_src = '" + img_src +"'";

        String activityname = "";
        Log.i(this.getClass().getName(), "설렉트문!! --> " + selectImgAll);
        Log.i(this.getClass().getName(), "이미지 리소스1!! --> " + imgResource[8]);

        Cursor cursor2 = db.rawQuery(selectImgAll,null);
        cursor2.moveToNext();
        activityname = cursor2.getString(1);

        //모든 행동을 가져오기 위한 코드
        String selectdata = "Select * FROM time_db WHERE activityname = '" + activityname + "'";
        cursor2 = db.rawQuery(selectdata,null);
        int total_second = 0;

        while (cursor2.moveToNext()){
            String timedata = cursor2.getString(4);
            String timedata1 = timedata.split(":")[0];
            String timedata2 = timedata.split(":")[1];
            String timedata3 = timedata.split(":")[2];
            total_second += Integer.parseInt(timedata1)*3600+
                            Integer.parseInt(timedata2)*60+
                            Integer.parseInt(timedata3);
        }
        Log.i(this.getClass().getName(), "total_second ==> " + total_second);

        //계산해서 값넣어주기
        txtDay.setText(total_second / 86400 +"일");
        txtHour.setText(total_second / 3600 +"시간");
        txtMinute.setText(total_second / 60 +"분");
        txtWage.setText(total_second / 3600 *8590 +"원 벌기");
        txtCurry.setText(total_second / 180 + "개 제조");
        txtSing.setText(total_second / 229 +"곡 부르기");
        txtPcRoom.setText(total_second / 3600 * 1000 +"원");


        db.close();
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
            case R.id.nav_refresh:
                Log.i(this.getClass().getName(), "새로고침 클릭");
                refresh();
                break;

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

    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this);
        ft.attach(this);
        ft.commit();
    }

}