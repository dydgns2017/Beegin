package com.sungkyul.aa;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddWorkActivity extends Activity {

    // 활동을 추가할때 사용하는 클래스 입니다.


    Button btnadd;
    EditText edtName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);


        btnadd = (Button)findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName = (EditText)findViewById(R.id.edtName);
                Option option = new Option(edtName.getText().toString(),"test");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("class" ,option);

                startActivity(intent);
                finish();
            }
        });




    }


}
