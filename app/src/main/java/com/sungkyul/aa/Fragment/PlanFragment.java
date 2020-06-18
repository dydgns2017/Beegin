package com.sungkyul.aa.Fragment;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.aa.R;
import com.sungkyul.aa.Result.ResultItem;
import com.sungkyul.aa.Result.ResultItemView;
import com.sungkyul.aa.myDBHelper;
import com.sungkyul.aa.planFragment.ListViewAdapter;
import com.sungkyul.aa.planFragment.ListViewItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {



    View view;

    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, null);
        ListView listview ;
        ListViewAdapter adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "Box", "Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "Ind", "Assignment Ind Black 36dp") ;

        listview.setAdapter(adapter);

//        return inflater.inflate(R.layout.fragment_plan, container, false);
        return view;
    }



}