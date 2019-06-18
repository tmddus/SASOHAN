package com.example.sasohan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends Fragment  {

    private ImageButton btn_gowrite;
    private ArrayList<String> list = new ArrayList<>();
    View view;
    public ListActivity() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.saranghae_list, container, false);

        //글쓰기 버튼
        btn_gowrite = view.findViewById(R.id.btn_gowrite);
        btn_gowrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActibity.class);
                startActivity(intent);
            }
        });
        //글 목록
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        for (int i = 0; i < 20; i++) {
            list.add("item " + i); //임시데이터
        }
        ListView listview = (ListView) view.findViewById(R.id.listview) ;
        listview.setAdapter(adapter);

        return view;
    }


}