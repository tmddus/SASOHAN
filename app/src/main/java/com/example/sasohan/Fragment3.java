package com.example.sasohan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment3 extends Fragment {
    View view;
    TextView dayText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        dayText = view.findViewById(R.id.today_rememberDay_frag3);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);


    }

}