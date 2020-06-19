package com.sungkyul.aa.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.aa.R;
import com.sungkyul.aa.myDBHelper;
import com.sungkyul.aa.planFragment.AddPlanActivity;
import com.sungkyul.aa.planFragment.ListViewAdapter;
import com.sungkyul.aa.planFragment.TimeProcesser;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {



    View view;
    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;
    int i=0;
    int length;

    TimeProcesser TP = new TimeProcesser();
    FloatingActionButton btnAddplan;
    Button btnCompletion;
    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, null);

        myDBHelper = new myDBHelper(getActivity());
        db = myDBHelper.getReadableDatabase();



        ListView listview ;
        ListViewAdapter adapter = new ListViewAdapter(getActivity());
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

        Cursor cursor;
        cursor = db.rawQuery("SELECT planname, activityname, img_src, timegoal, currenttime FROM user_plan;", null);

        while(cursor.moveToNext()){

           String planname = cursor.getString(0);
           String activityName = cursor.getString(1);
           int img_src = cursor.getInt(2);
           String timeGoal = cursor.getString(3);
           String StartTime = cursor.getString(4);
           String currentTime = TP.Timeprcess(getActivity(), activityName, StartTime);
           double progress = Integer.parseInt(currentTime)*100/Integer.parseInt(timeGoal);
           int int_progress = (int)progress;

            Log.i(this.getClass().getName(), "currentTime ==>" + Integer.parseInt(currentTime)  + "   TimeGoal ==>" + Integer.parseInt(timeGoal) +
                    "  progress ==> " + progress + "  int ->" + int_progress);

            adapter.addItem(ContextCompat.getDrawable(getActivity(), img_src),
                   planname, currentTime, timeGoal,  int_progress);

            i++;
            length = i;
        }


        listview.setAdapter(adapter);

        return view;
    }



}