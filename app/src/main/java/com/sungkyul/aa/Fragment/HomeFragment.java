package com.sungkyul.aa.Fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sungkyul.aa.AddWorkActivity;
import com.sungkyul.aa.MainActivity;
import com.sungkyul.aa.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, null);

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterID[position]);


            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "test", Toast.LENGTH_LONG).show();

                }
            });

            return imageView;
        }
    }
}
