package com.example.sasohan;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Console;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int FRAGMENT = 0;
    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;

    private  TextView btn_Home;
    private ImageButton ibt_tab1,ibt_tab2,ibt_tab3;
    private TextView tbt_tab1,tbt_tab2,tbt_tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);

//        btn_Home = findViewById(R.id.btnHome);
//        btn_Home.setOnClickListener(this);

        // 위젯에 대한 참조
        ibt_tab1 = (ImageButton)findViewById(R.id.ibt_tab1);
        ibt_tab2 = (ImageButton)findViewById(R.id.ibt_tab2);
        ibt_tab3 = (ImageButton)findViewById(R.id.ibt_tab3);

        tbt_tab1 = (TextView)findViewById(R.id.txt_tab1);
        tbt_tab2 = (TextView)findViewById(R.id.txt_tab2);
        tbt_tab3 = (TextView)findViewById(R.id.txt_tab3);

        // 탭 버튼에 대한 리스너 연결
        ibt_tab1.setOnClickListener(this);
        ibt_tab2.setOnClickListener(this);
        ibt_tab3.setOnClickListener(this);
        tbt_tab1.setOnClickListener(this);
        tbt_tab2.setOnClickListener(this);
        tbt_tab3.setOnClickListener(this);

        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 정함
        callFragment(FRAGMENT);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.custom_title, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHome:
                // '홈' 클릭 시 '프래그먼트' 호출
                callFragment(FRAGMENT);
                break;
            case R.id.ibt_tab1 : case R.id.txt_tab1:
                // '버튼1' 클릭 시 '프래그먼트1' 호출
                callFragment(FRAGMENT1);
                break;

            case R.id.ibt_tab2 :case R.id.txt_tab2:
                // '버튼2' 클릭 시 '프래그먼트2' 호출
                callFragment(FRAGMENT2);
                break;
            case R.id.ibt_tab3 :case R.id.txt_tab3:
                // '버튼2' 클릭 시 '프래그먼트3' 호출
                callFragment(FRAGMENT3);
                break;
        }
    }

    private void callFragment(int frament_no){

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no){
            case 0:
                // '프래그먼트1' 호출
                FragDefault dfragment = new FragDefault();
                transaction.replace(R.id.fragment_container, dfragment);
                transaction.commit();
                break;
            case 1:
                // '프래그먼트1' 호출
                ListActivity fragment1 = new ListActivity();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // '프래그먼트2' 호출
                GallayFragment fragment2 = new GallayFragment();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

            case 3:
                // '프래그먼트2' 호출
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.fragment_container, fragment3);
                transaction.commit();
                break;
        }

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }

}




