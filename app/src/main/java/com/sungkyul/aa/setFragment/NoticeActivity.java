package com.sungkyul.aa.setFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sungkyul.aa.R;

public class NoticeActivity extends Activity {

//        private View header;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.notice);
            setTitle("공지 사항");

            //리스트뷰에 들어갈 문자들
            final String[] notice = {"Beegin 시스템 점검 - 5월 24일(일)" ,"Beegin 시스템 점검 - 5월 15일(금)",
                    "Beegin 시스템 점검 - 5월 8일(금)" ,"Beegin 시스템 점검 - 5월 1일(금)",
                    "Beegin 시스템 점검 - 4월 24일(금)" ,"Beegin 시스템 점검 - 4월 17일(금)"};

            //            header = getLayoutInflater().inflate(R.layout.notice,null,false);


            ListView listnotice = (ListView)findViewById(R.id.Noitcelist);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, notice);

            listnotice.setAdapter(adapter);

            //클릭했을때 이벤트
            listnotice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),
                            notice[position] +"이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

    }
}
