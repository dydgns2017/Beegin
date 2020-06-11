package com.sungkyul.aa.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sungkyul.aa.R;
import com.sungkyul.aa.setFragment.HelpActivity;
import com.sungkyul.aa.setFragment.LogoutActivity;
import com.sungkyul.aa.setFragment.NoticeActivity;
import com.sungkyul.aa.setFragment.QuestionActivity;
import com.sungkyul.aa.setFragment.SettingActivity;


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

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_set, container, false);


        //공지사항 창
        LinerNotice = rootView.findViewById(R.id.LinerNotice);

        LinerNotice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerNotice 클릭");

                Intent intent = new Intent(getActivity().getApplication(), NoticeActivity.class);

                startActivity(intent);
            }
        });

        //질문과 답변 창
        LinerQuestion = rootView.findViewById(R.id.LinerQuestion);

        LinerQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerQuestion 클릭");

                Intent intent = new Intent(getActivity().getApplication(), QuestionActivity.class);

                startActivity(intent);
            }
        });

        //도움말 창
        LinerHelp = rootView.findViewById(R.id.LinerHelp);

        LinerHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerHelp 클릭");

                Intent intent = new Intent(getActivity().getApplication(), HelpActivity.class);

                startActivity(intent);
            }
        });

        //설정창
        LinerSetting = rootView.findViewById(R.id.LinerSetting);

        LinerSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerSetting 클릭");

                Intent intent = new Intent(getActivity().getApplication(), SettingActivity.class);

                startActivity(intent);
            }
        });

        //로그아웃 창
        LinerLogout = rootView.findViewById(R.id.LinerLogout);

        LinerLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerLogout 클릭");

                Intent intent = new Intent(getActivity().getApplication(), LogoutActivity.class);

                startActivity(intent);
            }
        });


        return rootView;



    }

}
