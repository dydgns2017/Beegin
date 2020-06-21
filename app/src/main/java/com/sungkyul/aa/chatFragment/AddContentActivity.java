package com.sungkyul.aa.chatFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import com.sungkyul.aa.R;
import com.sungkyul.aa.httpServlet.phpApi;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContentActivity extends Activity {
    EditText titleInput, contentInput;
    Button buttonApplyNewBoard;
    AlertDialog alertDialog;
    String username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontent);

        titleInput = (EditText) findViewById(R.id.titleInput);
        contentInput = (EditText) findViewById(R.id.contentInput);
        buttonApplyNewBoard = (Button) findViewById(R.id.ButtonApplyNewBoard);
        alertDialog = new AlertDialog.Builder(AddContentActivity.this).create();

        SharedPreferences pref = getSharedPreferences("userData", Context.MODE_PRIVATE);
        username = pref.getString("username", null);

        buttonApplyNewBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 아무런 입력이 되어있지 않은 상태
                if ( titleInput.getText().toString().equals("") || contentInput.getText().toString().equals("") ){
                    alertDialog.setMessage("모든 정보를 입력해주세요.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "확인",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }

                // timestamp
                String timestamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

                // data set
                String serviceName = "writeBoard";
                String Servicedata;
                Servicedata = "func=" + serviceName;
                Servicedata += "&username=" + username;
                Servicedata += "&title=" + titleInput.getText().toString();
                Servicedata += "&content=" + contentInput.getText().toString();
                Servicedata += "&timestamp=" + timestamp;

                // return json data
                JSONObject data = phpApi.POSTsend(Servicedata);
                System.err.println("Data : " + data);
                finish();
            }
        });
    }

}
