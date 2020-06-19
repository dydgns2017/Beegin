package com.sungkyul.aa.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.aa.AddWorkActivity;
import com.sungkyul.aa.R;
import com.sungkyul.aa.Result.ResultItem;
import com.sungkyul.aa.Result.ResultItemView;
import com.sungkyul.aa.myDBHelper;
import com.sungkyul.aa.planFragment.AddPlanActivity;
import com.sungkyul.aa.planFragment.ListViewAdapter;
import com.sungkyul.aa.planFragment.ListViewItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {



    View view;
    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;

    int i=0;
    int length;

    FloatingActionButton btnAddplan;

    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, null);

        ListView listview ;
        ListViewAdapter adapter = new ListViewAdapter();
        btnAddplan = (FloatingActionButton) view.findViewById(R.id.btnAddplan);
        btnAddplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddPlanActivity.class);
                startActivity(intent);
            }
        });
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);

        myDBHelper = new myDBHelper(getActivity());
        db = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT planname, activityname, img_src, timegoal FROM user_plan;", null);

        while(cursor.moveToNext()){

           String planname = cursor.getString(0);
           String activityName = cursor.getString(1);
           int img_src = cursor.getInt(2);
           String timgGoal = cursor.getString(3);

//            public void addItem(Drawable icon, String title, String  currentTime, String GoalTime , int progressBar) {
            // 첫 번째 아이템 추가.
            adapter.addItem(ContextCompat.getDrawable(getActivity(), img_src),
                   planname, "00:00", timgGoal, 30);
/*
            Log.i(this.getClass().getName(),"이게 제일 중요!! -> "+ posterID[i] + posterText[i] + "-->" + i);*/
            i++;
            length = i;
        }


        listview.setAdapter(adapter);

        return view;
    }



}