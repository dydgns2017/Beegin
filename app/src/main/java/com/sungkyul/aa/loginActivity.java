package com.sungkyul.aa;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btnLogin = (Button)findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtid = (EditText)findViewById(R.id.EdtId);
                EditText edtpwd = (EditText)findViewById(R.id.EdtPassward);

                if(edtid.getText().toString().equals("test") && edtpwd.getText().toString().equals("test")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}