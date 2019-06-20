package com.example.sasohan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends Fragment  {

    private ImageButton btn_gowrite;
    private ArrayList<String> list = new ArrayList<>();
    View view;
    Intent intent;
    String title,contents;
    ListView listview ;
    ListViewAdapter adapter;
    public ListActivity() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.saranghae_list, container, false);


        //글 목록

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("사랑하는 우리엄마", "2019/06/10") ; //db에서 (제목,날짜) 가져오기
        // 두 번째 아이템 추가.
        adapter.addItem("오늘 가족에게 속상했던 일들", "2019/06/02") ;
        // 세 번째 아이템 추가.
        adapter.addItem("언니 앞으로는 싸우지 말자", "2019/05/11") ;
        adapter.addItem("할머니 아프지 마세요", "2019/05/09") ;
        adapter.addItem("힘들었을 나에게, 수고했어", "2019/05/01") ;
        adapter.notifyDataSetChanged();


        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;

            }
        }) ;

        //글쓰기 버튼
        btn_gowrite = view.findViewById(R.id.btn_gowrite);
        btn_gowrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), WriteActibity.class);
                startActivity(intent);

                refresh();
            }
        });

//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
//        for (int i = 0; i < 20; i++) {
//            list.add("item " + i); //임시데이터
//        }
//        ListView listview = (ListView) view.findViewById(R.id.listview) ;
//        listview.setAdapter(adapter);

        return view;
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
                convertView = inflater.inflate(R.layout.listview_item, parent, false);
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
        adapter.addItem(title,contents);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }
}