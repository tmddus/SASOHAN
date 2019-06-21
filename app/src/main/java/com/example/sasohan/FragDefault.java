package com.example.sasohan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FragDefault extends Fragment  {

    private ImageButton btn_gophone;
    private ArrayList<String> list = new ArrayList<>();
    View view;
    Intent intent;
    String name,number;
    ListView listview ;
    ListViewAdapter adapter;
    public FragDefault() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.frag_default, container, false);


        출처: https://blog.opid.kr/250 [opid's document]

        //글 목록

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        //db에서 (제목,날짜) 가져오기
        // 임시데이터
        adapter.addItem("엄마", "010-1234-5678") ;
        adapter.addItem("아빠", "010-9876-5432") ;
        adapter.addItem("언니", "010-2345-6789") ;
        adapter.addItem("오빠", "010-8765-4321") ;
        adapter.notifyDataSetChanged();


        //글쓰기 버튼
        btn_gophone = view.findViewById(R.id.btn_gophone);
        btn_gophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
                adapter.addItem(name, number) ;
            }
        });


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            Cursor cursor = getActivity().getContentResolver().query(intent.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            name = cursor.getString(0);        //0은 이름을 얻어옵니다.
            number = cursor.getString(1);    //1은 번호를 받아옵니다.
            adapter.addItem(name,number);
            cursor.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static class ListViewItem {
        private String titleStr ;
        private String descStr ;

        public void setTitle(String title) {
            titleStr = title ;
        }
        public void setDesc(String desc) {
            descStr = desc ;
        }

        public String getTitle() {
            return this.titleStr ;
        }
        public String getDesc() {
            return this.descStr ;
        }
    }

    public class ListViewAdapter extends BaseAdapter {
        // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
        private ArrayList<ListActivity.ListViewItem> listViewItemList = new ArrayList<ListActivity.ListViewItem>();

        // ListViewAdapter의 생성자
        public ListViewAdapter() {

        }

        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {
            return listViewItemList.size();
        }

        // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.call_list_item, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            TextView titleTextView = (TextView) convertView.findViewById(R.id.textTitle); //제목
            TextView descTextView = (TextView) convertView.findViewById(R.id.textDate); //날짜

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListActivity.ListViewItem listViewItem = listViewItemList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            titleTextView.setText(listViewItem.getTitle());
            descTextView.setText(listViewItem.getDesc());

            return convertView;
        }

        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
        @Override
        public long getItemId(int position) {
            return position;
        }

        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public Object getItem(int position) {
            return listViewItemList.get(position);
        }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(String title, String desc) {
            ListActivity.ListViewItem item = new ListActivity.ListViewItem();

            item.setTitle(title);
            item.setDesc(desc);

            listViewItemList.add(item);
        }

    }
    //새로고침(변경사항반영)
    private void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }
}