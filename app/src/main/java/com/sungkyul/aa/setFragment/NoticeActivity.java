package com.sungkyul.aa.setFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sungkyul.aa.MainActivity;
import com.sungkyul.aa.R;

public class NoticeActivity extends Activity {

    private View header;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        setTitle("공지 사항");

        //리스트뷰에 들어갈 문자들
        final String[] notice = {"Beegin 시스템 점검 - 2020.05.29(일)" ,"Beegin 시스템 점검 - 2020.06.05(금)",
                "Beegin 시스템 점검 - 2020.06.07(금)" ,"Beegin 시스템 점검 - 2020.06.09(금)",
                "Beegin 시스템 점검 - 2020.06.12일(금)" ,"Beegin 시스템 점검 - 2020.06.16일(금)",
                "Beegin 시스템 점검 - 2020.06.18일(금)"};

        header = getLayoutInflater().inflate(R.layout.notice,null,false);


        ListView listnotice = (ListView)findViewById(R.id.Noitcelist);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, notice);

        listnotice.setAdapter(adapter);

        //클릭했을때 이벤트
        listnotice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                        notice[position] +"이 클릭되었습니다." +  position, Toast.LENGTH_SHORT).show();
                //숫자뺀게 밑에꺼 위에껀 안뺀거
                Toast.makeText(getApplicationContext(),
                        notice[position] +"이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                switch (position) {

                    case 0 :
                        show("첫 어플리케이션 구조 생성", "2020.05.29 \n\n 1. 어플리케이션 구조 생성하였습니다.");
                        break;
                    case 1 :
                        show("로그인 xml 생성", "2020.06.05 \n\n 1. 로그인/아웃 구조를 생성하였습니다.");
                        break;
                    case 2 :
                        show("메인화면 생성", "2020.06.07 \n\n 1. 메인화면 구조를 생성하였습니다.");
                        break;
                    case 3 :
                        show("스플레쉬 이미지 생성", "2020.06.09 \n\n 1. 스플레쉬 화면을 만들었습니다.");
                        break;
                    case 4 :
                        show("활동 입력값을 통한 결과창 생성", "2020.06.12 \n\n 1. 활동 입력값을 기반으로 결과창을 생성하였습니다. \n 2.뒤로가기를 두번눌러야 뒤로갈수 있도록 설계하였습니다. ");
                        break;
                    case 5 :
                        show("활동 Sqlite 생성", "2020.06.16 \n\n 1. 여러가지 활동 기록을 담을수 있도록 DB를 설계 하였습니다.");
                        break;
                    case 6 :
                        show("여러가지 기능 추가","2020.06.18 \n\n 1. 결과창을 다양한 형태로 볼수있도록 하였습니다. \n 2. 계획 창을 통해서 사용자가 목표를 달성할 수 있습니다." +
                                "\n 3. 자유게시판 생성 및 설정창 구현하였습니다.  " );
                        break;
                }


            }
        });

    }

    void show(String title, String content)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(content);

        builder.setNegativeButton("닫기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }
}