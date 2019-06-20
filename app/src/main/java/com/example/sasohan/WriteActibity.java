package com.example.sasohan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class WriteActibity extends AppCompatActivity {
    Button addbtn;
    String title, contents;
    EditText ed_title, ed_contents;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saranghae_wirte);

        addbtn = findViewById(R.id.btn_add);
        ed_title = findViewById(R.id.edit_title);
        ed_contents = findViewById(R.id.edit_content);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db에 글 추가


                //db삽입성공시
                intent = getIntent();
                title = ed_title.getText().toString();
                contents = ed_contents.getText().toString();
                intent.putExtra("title",title);
                intent.putExtra("contents",contents);
                Toast toast = Toast.makeText(getApplicationContext(),"일기가 등록되었습니다.",Toast.LENGTH_SHORT);
                toast.show();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
