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

    private long backKeyPressedTime = 0;
    private Toast toast;

    private ActionBar bar;
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private PlanFragment planFragment;
    private SetFragment setFragment;
    private ChatFragment chatFragment;
    private ResultFragment resultFragment;

    private Activity activity;
    public static myDBHelper myDBHelper;
    public static SQLiteDatabase db;
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        homeFragment = new HomeFragment();
        //DB생성을 위한 코드 WY : 06-16 추가
        myDBHelper = new myDBHelper(this);
               db = myDBHelper.getWritableDatabase();
//        db.execSQL("INSERT INTO time_db(activityname, timestart, timeend, timedata) values ('여가활동', '2020/06/17 11:22:00', '2020/06/17 12:00:00', '0:38:0' )");
//        db.execSQL("INSERT INTO time_db(activityname, timestart, timeend, timedata) values ('인터넷', '2020/06/16 11:22:00', '2020/06/16 12:00:00', '0:38:0' )");
//        db.execSQL("INSERT INTO time_db(activityname, timestart, timeend, timedata) values ('여가활동', '2020/06/05 11:22:00', '2020/06/05 12:00:00', '0:38:0' )");


        db.close();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, homeFragment).commit();
        setBottomBar();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }


    @Override
    protected void onPause() {
        super.onPause();


    }

    public void setBottomBar(){
        mMainFrame = (FrameLayout)findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);

        activity = this;

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        setTitle("홈");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#87cefa")));

                        getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.littledeep_bee_style1));


                        if(homeFragment == null) {
                            homeFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, homeFragment).commit();
                        }

                        Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
                        if(homeFragment != null) getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                        if(planFragment != null) getSupportFragmentManager().beginTransaction().hide(planFragment).commit();
                        if(setFragment != null) getSupportFragmentManager().beginTransaction().hide(setFragment).commit();
                        if(resultFragment != null) getSupportFragmentManager().beginTransaction().hide(resultFragment).commit();
                        if(chatFragment != null) getSupportFragmentManager().beginTransaction().hide(chatFragment).commit();
/*
                        ft.replace(R.id.main_frame, homeFragment);
                        ft.commit();*/

                        return true;


                    case R.id.nav_plan:
                        setTitle("계획");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#87cefa")));

                        if(planFragment == null) {
                            planFragment = new PlanFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, planFragment).commit();
                        }
                        if(planFragment != null) getSupportFragmentManager().beginTransaction().show(planFragment).commit();
                        if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                        if(setFragment != null) getSupportFragmentManager().beginTransaction().hide(setFragment).commit();
                        if(resultFragment != null) getSupportFragmentManager().beginTransaction().hide(resultFragment).commit();
                        if(chatFragment != null) getSupportFragmentManager().beginTransaction().hide(chatFragment).commit();
/*
                        ft.replace(R.id.main_frame, planFragment);
                        ft.commit();
*/

                        return true;


                    case R.id.nav_result :
                        setTitle("결과");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#87cefa")));

                        if(resultFragment == null) {
                            resultFragment = new ResultFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, resultFragment).commit();
                        }
                        if(resultFragment != null) getSupportFragmentManager().beginTransaction().show(resultFragment).commit();
                        if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                        if(setFragment != null) getSupportFragmentManager().beginTransaction().hide(setFragment).commit();
                        if(planFragment != null) getSupportFragmentManager().beginTransaction().hide(planFragment).commit();
                        if(chatFragment != null) getSupportFragmentManager().beginTransaction().hide(chatFragment).commit();

                      /*  ft.replace(R.id.main_frame, resultFragment);
                        ft.commit();*/
                        return true;

                    case R.id.nav_chat:
                        setTitle("채팅");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#87cefa")));

                        if(chatFragment == null) {
                            chatFragment = new ChatFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, chatFragment).commit();
                        }
                        if(chatFragment != null) getSupportFragmentManager().beginTransaction().show(chatFragment).commit();
                        if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                        if(setFragment != null) getSupportFragmentManager().beginTransaction().hide(setFragment).commit();
                        if(planFragment != null) getSupportFragmentManager().beginTransaction().hide(planFragment).commit();
                        if(resultFragment != null) getSupportFragmentManager().beginTransaction().hide(resultFragment).commit();


/*                        ft.replace(R.id.main_frame, chatFragment);
                        ft.commit();*/
                        return true;


                    case R.id.nav_set:
                        setTitle("환경설정");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#87cefa")));

                        if(setFragment == null) {
                            setFragment = new SetFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, setFragment).commit();
                        }
                        if(setFragment != null) getSupportFragmentManager().beginTransaction().show(setFragment).commit();
                        if(homeFragment != null) getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
                        if(chatFragment != null) getSupportFragmentManager().beginTransaction().hide(chatFragment).commit();
                        if(planFragment != null) getSupportFragmentManager().beginTransaction().hide(planFragment).commit();
                        if(resultFragment != null) getSupportFragmentManager().beginTransaction().hide(resultFragment).commit();
/*                        ft.replace(R.id.main_frame, setFragment);
                        ft.commit();*/
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
