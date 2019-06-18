package com.example.sasohan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class WriteActibity extends AppCompatActivity {
    Button addbtn;
    public ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saranghae_wirte);


        addbtn = findViewById(R.id.btn_add);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db에 글 추가


                //db삽입성공시
                Toast toast = Toast.makeText(getApplicationContext(),"일기가 등록되었습니다.",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
    }
}
