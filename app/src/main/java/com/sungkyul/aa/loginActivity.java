package com.sungkyul.aa;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

public class loginActivity extends Activity {


    Button btnSignUp;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText id = (EditText)findViewById(R.id.id);
                EditText pass = (EditText)findViewById(R.id.password);

                    if("test".equals(id.getText().toString().trim()) && "test".equals(pass.getText().toString().trim())) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "아이디 비밀번호가 틀렸어? (id,pwd : test)",Toast.LENGTH_LONG).show();
                    }


            }
        });



        btnSignUp = (Button)findViewById(R.id.btnSignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "click_signUp", Toast.LENGTH_LONG).show();
               Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


    }


}