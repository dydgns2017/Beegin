package com.sungkyul.aa.setFragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.sungkyul.aa.Fragment.SetFragment;
import com.sungkyul.aa.HelpFragment.HFragment1;
import com.sungkyul.aa.HelpFragment.HFragment2;
import com.sungkyul.aa.HelpFragment.HFragment3;
import com.sungkyul.aa.HelpFragment.HFragment4;
import com.sungkyul.aa.HelpFragment.HFragment5;
import com.sungkyul.aa.HelpFragment.HFragment6;
import com.sungkyul.aa.MainActivity;


public class HelpActivity extends AppIntro {

    Fragment hfragment1 = new HFragment1();
    Fragment hfragment2 = new HFragment2();
    Fragment hfragment3 = new HFragment3();
    Fragment hfragment4 = new HFragment4();
    Fragment hfragment5 = new HFragment5();
    Fragment hfragment6 = new HFragment6();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        addSlide(hfragment1);
        addSlide(hfragment2);
        addSlide(hfragment3);
        addSlide(hfragment4);
        addSlide(hfragment5);
        addSlide(hfragment6);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startMainActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void startMainActivity(){
//        Intent intent = new Intent(HelpActivity.this, SetFragment.class);
//        startActivity(intent);
        finish();
    }
}
