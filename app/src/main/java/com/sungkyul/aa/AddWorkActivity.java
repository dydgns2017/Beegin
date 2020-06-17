package com.sungkyul.aa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AddWorkActivity extends Activity {

    // 활동을 추가할때 사용하는 클래스 입니다.


    Button btnadd;
    EditText edtName;
    ImageButton btnaddImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);

        btnaddImg = (ImageButton)findViewById(R.id.btnaddimage);
        btnaddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"test", Toast.LENGTH_LONG).show();
                showAlertDialog();
            }
        });

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

    private void showAlertDialog() {
        // Prepare grid view
        GridView gridView = new GridView(this);
        ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();

        Integer[] imgResource = {R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01,
                R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01,
                R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01,
                R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov01, R.drawable.mov02, };

        for (int i = 0; i < imgResource.length; i++) {
            picArr.add(BitmapFactory.decodeResource(getResources(), imgResource[i]));
        }


        gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, picArr));
        gridView.setNumColumns(5);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // do something here
            }
        });

        // Set grid view to alertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(gridView);
        builder.setTitle("이미지 선택");
        builder.show();
    }


}
