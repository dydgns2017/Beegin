package com.sungkyul.aa;

/*
 2020. 06. 25. sqlite 추가 및 list adapter 추가
 추가 및 수정된 클래스
    - AddWorkActivity.java
    - Option.java
    - activity_addwork.xml
    - list_item.xml

    - MainActivity
    - activity_main.xml
 */


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sungkyul.aa.Fragment.ChatFragment;
import com.sungkyul.aa.Fragment.HomeFragment;
import com.sungkyul.aa.Fragment.PlanFragment;
import com.sungkyul.aa.Fragment.ResultFragment;
import com.sungkyul.aa.Fragment.SetFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ActionBar bar;
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private PlanFragment planFragment;
    private SetFragment setFragment;
    private ChatFragment chatFragment;
    private ResultFragment resultFragment;


    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        setBottomBar();

    }


    public void setBottomBar(){
        mMainFrame = (FrameLayout)findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);


        homeFragment = new HomeFragment();
        planFragment = new PlanFragment();
        setFragment = new SetFragment();
        resultFragment = new ResultFragment();
        chatFragment = new ChatFragment();

        activity = this;

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        setTitle("홈");
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.PRIMARY_STAUTS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#008577")));

                        getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.littledeep_bee_style1));
                        ft.replace(R.id.main_frame, homeFragment);
                        ft.commit();

                        return true;


                    case R.id.nav_plan:
                        setTitle("계획");
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.ACCENT_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#D81B60")));
                        ft.replace(R.id.main_frame, planFragment);
                        ft.commit();

                        return true;


                    case R.id.nav_result :
                        setTitle("결과");
                        mMainNav.setItemBackgroundResource(R.color.colorTest);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.YELLOW_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#FFFFAA00")));
                        ft.replace(R.id.main_frame, resultFragment);
                        ft.commit();
                        return true;

                    case R.id.nav_chat:
                        setTitle("채팅");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#42A5F5")));
                        ft.replace(R.id.main_frame, chatFragment);
                        ft.commit();
                        return true;


                    case R.id.nav_set:
                        setTitle("환경설정");
                        mMainNav.setItemBackgroundResource(R.color.colorPurple);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.PURPLE_STAUTS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#7E57C2")));
                        ft.replace(R.id.main_frame, setFragment);
                        ft.commit();
                        return true;

                    default:
                        return false;

                }
            }
        });
    }


/*    private void setFragment(Fragment fragment){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();

    }*/
}
