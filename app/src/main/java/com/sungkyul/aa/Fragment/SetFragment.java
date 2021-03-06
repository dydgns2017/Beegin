package com.sungkyul.aa.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sungkyul.aa.R;
import com.sungkyul.aa.setFragment.HelpActivity;
import com.sungkyul.aa.setFragment.NoticeActivity;
import com.sungkyul.aa.setFragment.QuestionActivity;
import com.sungkyul.aa.setFragment.SettingActivity;

import com.sungkyul.aa.loginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends Fragment {

    LinearLayout LinerNotice, LinerQuestion, LinerHelp, LinerSetting, LinerLogout;
    LinearLayout LinerOutputData, LinerInputData;
    TextView fragmentTextViewLogin;
    ImageView fragmentImageViewLogin;
    SharedPreferences pref;

    public SetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_set, container, false);
        pref = this.getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
        // 로그인 확인
        fragmentTextViewLogin = (TextView) rootView.findViewById(R.id.fragmentSetloginText);
        fragmentImageViewLogin = (ImageView) rootView.findViewById(R.id.fragmentSetLoginImageView);
        if ( pref.contains("username") ){
            fragmentTextViewLogin.setText(" 로그아웃");
            fragmentImageViewLogin.setImageResource(R.drawable.logout);
        }

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

        //로그인 및 로그아웃 창
        LinerLogout = rootView.findViewById(R.id.LinerLogout);

        LinerLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "LinerLogout 클릭");
                SharedPreferences.Editor editor = pref.edit();
                if ( !(pref.contains("username")) ){
                    Intent intent = new Intent(getActivity().getApplication(), loginActivity.class);
                    startActivity(intent);
                } else {
                    // logout
                    Log.i(this.getClass().getName(), "로그아웃 동작");
                    editor.remove("username").commit();
                    editor.remove("password").commit();
                    fragmentTextViewLogin.setText(" 로그인");
                    fragmentImageViewLogin.setImageResource(R.drawable.login);

                }
            }
        });

        //데이터 내려받기
        LinerInputData = rootView.findViewById(R.id.LinerInputData);

        LinerInputData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "데이터를 내려받았습니다.",Toast.LENGTH_SHORT).show();
            }
        });


        //데이터 백업하기(업로드)

        LinerOutputData = rootView.findViewById(R.id.LinerOutdata);

        LinerOutputData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "데이터를 백업하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;



    }

}
