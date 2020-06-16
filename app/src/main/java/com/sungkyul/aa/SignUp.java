package com.sungkyul.aa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.sungkyul.aa.httpServlet.phpApi;

import org.json.JSONException;
import org.json.JSONObject;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class SignUp extends Activity {
    EditText edtFullName, edtUserName, edtEmail, password, password_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //  set ProgressDialog..
        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        edtFullName = (EditText)findViewById(R.id.edtFullName);
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.password);
        password_again = (EditText)findViewById(R.id.password_again);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Progressbar start
                ProgressDialog progressDialog;
                progressDialog = ProgressDialog.show(SignUp.this, "", "Loading..");

                // data set
                String serviceName = "signup";
                String Servicedata;
                Servicedata = "func=" + serviceName;
                // editText data set
                if ( edtFullName.getText().toString().equals("") || edtUserName.getText().toString().equals("") ||
                        edtEmail.getText().toString().equals("") || password.getText().toString().equals("") || password_again.getText().toString().equals("")){
                    progressDialog.dismiss();
                    AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("모든 정보를 입력해주세요.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }
                Servicedata += "&fullname=" + edtFullName.getText().toString();
                Servicedata += "&username=" + edtUserName.getText().toString();
                Servicedata += "&email=" + edtEmail.getText().toString();
                Servicedata += "&password=" + password.getText().toString();
                Servicedata += "&passagain=" + password_again.getText().toString();

                // return json data
                JSONObject data = phpApi.POSTsend(Servicedata);
                System.err.println("Data : " + data);

                // prev process
                try {
                    if ( data.toString().equals("") ||  data.getString("status") == "error" ){
                        progressDialog.dismiss();
                       return;
                    }
                    System.err.println("datagetString : " + data.getString("data"));
                    if ( data.getString("data").toString().equals("password is wrong") ){
                        progressDialog.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("패스워드를 다시 입력해주세요.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        System.err.println("패스워드를 다시 입력해주세요.");
                        return;
                    }
                    if ( data.getString("data").toString().equals("existsUser") ){
                        progressDialog.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("이미 가입된 회원입니다.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        System.err.println("가입된회원.");
                        return;
                    }
                    // 아이디 중복 막기
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // center process
                System.err.println("아이디 생성완료");

                // Progressbar exit
                progressDialog.dismiss();

                // LoginActivity 로 이동
                Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(intent);

                // 현재 엑티비티 종료
                finish();
            }
        });
    }
}
