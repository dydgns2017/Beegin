package com.sungkyul.aa.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sungkyul.aa.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends Fragment {

    LinearLayout LinerNotice, LinerQuestion, LinerHelp, LinerSetting, LinerLogout;
    public SetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set, container, false);
/*
        LinerNotice = (LinearLayout)getView().findViewById(R.id.LinerNotice);

        LinerNotice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerNotice 클릭");

                Intent intent = new Intent(getActivity().getApplication(), NoticeActivity.class);

                startActivity(intent);
            }
        });

        LinerQuestion = (LinearLayout)getView().findViewById(R.id.LinerQuestion);

        LinerQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerQuestion 클릭");

                Intent intent = new Intent(getActivity().getApplication(), QuestionActivity.class);

                startActivity(intent);
            }
        });

        LinerHelp = (LinearLayout)getView().findViewById(R.id.LinerHelp);

        LinerHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerHelp 클릭");

                Intent intent = new Intent(getActivity().getApplication(), HelpActivity.class);

                startActivity(intent);
            }
        });

        LinerSetting = (LinearLayout)getView().findViewById(R.id.LinerSetting);

        LinerSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerSetting 클릭");

                Intent intent = new Intent(getActivity().getApplication(), SettingActivity.class);

                startActivity(intent);
            }
        });

        LinerLogout = (LinearLayout)getView().findViewById(R.id.LinerLogout);

        LinerLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerLogout 클릭");

                Intent intent = new Intent(getActivity().getApplication(), LogoutActivity.class);

                startActivity(intent);
            }
        });*/
    }

}
