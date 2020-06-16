package com.sungkyul.aa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.sungkyul.aa.MainActivity;

public class SplashActivity extends Activity {
// 처음등장하는 이미지 시간지정 및 엑티비티 넘기는 클래스 입니다.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(500);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/*        startActivity(new Intent(this, loginActivity.class));*/
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}
