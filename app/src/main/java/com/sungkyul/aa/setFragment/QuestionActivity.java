package com.sungkyul.aa.setFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sungkyul.aa.R;

public class QuestionActivity extends Activity {

    Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "문의가 성공적으로 접수되었습니다!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
