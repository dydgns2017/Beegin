package com.sungkyul.aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sungkyul.aa.Fragment.ChatFragment;
import com.sungkyul.aa.Fragment.HomeFragment;
import com.sungkyul.aa.Fragment.PlanFragment;
import com.sungkyul.aa.Fragment.ResultFragment;
import com.sungkyul.aa.Fragment.SetFragment;

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
        setTitle("Home");



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
                        setTitle("Home");
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.PRIMARY_STAUTS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#008577")));

                        getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.littledeep_bee_style1));
                        ft.replace(R.id.main_frame, homeFragment);
                        ft.commit();

                        return true;


                    case R.id.nav_plan:
                        setTitle("Plan");
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.ACCENT_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#D81B60")));
                        ft.replace(R.id.main_frame, planFragment);
                        ft.commit();

                        return true;


                    case R.id.nav_result :
                        setTitle("Result");
                        mMainNav.setItemBackgroundResource(R.color.colorTest);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.YELLOW_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#FFFFAA00")));
                        ft.replace(R.id.main_frame, resultFragment);
                        ft.commit();
                        return true;

                    case R.id.nav_chat:
                        setTitle("Chatting");
                        mMainNav.setItemBackgroundResource(R.color.colorBlue);
                        Utils.setStatusBarColor(activity, Utils.StatusBarColorType.BLUE_STATUS_BAR);
                        getSupportActionBar().setBackgroundDrawable(
                                new ColorDrawable(Color.parseColor("#42A5F5")));
                        ft.replace(R.id.main_frame, chatFragment);
                        ft.commit();
                        return true;


                    case R.id.nav_set:
                        setTitle("Setting");
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
