package com.example.sasohan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Fragment1 extends Fragment  {

    private ImageButton add_btn;
View view;
    public Fragment1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.saranghae_list, container, false);
        add_btn = view.findViewById(R.id.btn_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActibity.class);
                startActivity(intent);
            }
        });
        return view;

    }


}