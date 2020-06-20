package com.sungkyul.aa.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.aa.Board.BoardItem;
import com.sungkyul.aa.Board.BoardItemView;
import com.sungkyul.aa.R;
import com.sungkyul.aa.Result.ResultItem;
import com.sungkyul.aa.chatFragment.AddContentActivity;
import com.sungkyul.aa.httpServlet.phpApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
// 게시판으로 사용될 클래스입니다.
public class ChatFragment extends Fragment {

    ListView boardList;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_chat, null,false);

        boardList = (ListView)rootview.findViewById(R.id.boardList);

        // 게시글 추가 버튼
        FloatingActionButton btnAddboard = (FloatingActionButton) rootview.findViewById(R.id.btnAddBoard);
        btnAddboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddContentActivity.class);
                startActivity(intent);
            }
        });

        final BoardAdapter adapter = new BoardAdapter();

        // POST 통신으로 게시글 가져오기
        String serviceName = "getBoard";
        String Servicedata;
        Servicedata = "func=" + serviceName;
        // return json data
        JSONObject data = phpApi.POSTsend(Servicedata);
        System.err.println("Data : " + data);
        try {
            JSONObject useData = data.getJSONObject("data");
            int all_count = useData.getInt("all_count");
            for(int i=0; i<all_count; i++){
                JSONObject getUserBoard = useData.getJSONObject("0");
                String username = getUserBoard.getString("username");
                String title = getUserBoard.getString("title");
                String timestamp = getUserBoard.getString("timestamp");
                adapter.addItem(new BoardItem(title, 1, username, timestamp));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        adapter.addItem(new BoardItem("제목 sample1", 1, "원용", "2020/06/19"));
//        adapter.addItem(new BoardItem("제목 sample2", 2, "혁주", "2020/06/19"));
//        adapter.addItem(new BoardItem("제목 sample3", 3, "용훈", "2020/06/19"));

        //어댑터연결
        boardList.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootview;
    }

    public class BoardAdapter extends BaseAdapter {
        ArrayList<BoardItem> boarditems = new ArrayList<BoardItem>();

        @Override
        public int getCount() {
            return boarditems.size();
        }

        public void addItem(BoardItem boardItem) { boarditems.add(boardItem);}


        @Override
        public Object getItem(int position) {
            return boarditems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BoardItemView boardItemView = null;

            if(convertView == null){
                boardItemView = new BoardItemView(getActivity());
            }else{
                boardItemView = (BoardItemView) convertView;

            }

            BoardItem boardItem = boarditems.get(position);
            boardItemView.setTitle(boardItem.getTitle());
            boardItemView.setCommentCount(boardItem.getCommentCount() +"");
            boardItemView.setDate(boardItem.getDate());
            boardItemView.setUserId(boardItem.getUserid());
            return boardItemView;
        }
    }



}
