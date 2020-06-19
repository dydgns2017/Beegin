package com.sungkyul.aa.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.aa.AddWorkActivity;
import com.sungkyul.aa.R;
import com.sungkyul.aa.chatFragment.AddContentActivity;


/**
 * A simple {@link Fragment} subclass.
 */
// 게시판으로 사용될 클래스입니다.
public class ChatFragment extends Fragment {
    View view;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_chat, null);

        FloatingActionButton btnAddboard = (FloatingActionButton) view.findViewById(R.id.btnAddBoard);
        btnAddboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddContentActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
