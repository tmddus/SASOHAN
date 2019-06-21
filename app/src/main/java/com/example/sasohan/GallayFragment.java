package com.example.sasohan;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GallayFragment extends Fragment {
    final int REQ_CODE_SELECT_IMAGE=100;
    View view;
    private ImageButton add_photo;
    private ArrayList<Integer> imgarray = new ArrayList<>();
    DisplayMetrics mMetrics;
    public GallayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sojunghae_1, container, false);
        // 1. 다량의 데이터
        // 2. Adapter
        // 3. AdapterView - GridView
//        int img[] = {
//                R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e
//        };
        imgarray.add( R.drawable.c);imgarray.add( R.drawable.e);imgarray.add( R.drawable.q);imgarray.add( R.drawable.e);imgarray.add( R.drawable.j);imgarray.add( R.drawable.q);imgarray.add( R.drawable.c);imgarray.add( R.drawable.e);

        // 커스텀 아답타 생성
        ImageAdapter adapter = new ImageAdapter (getContext());    // 데이터

        GridView gv = view.findViewById(R.id.gridview);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        //갤러리 열기
        add_photo = view.findViewById(R.id.btn_addphoto);
        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//            }
//        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(getActivity().getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());




                    //이미지 데이터를 비트맵으로 받아온다.

                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    ImageView image = (ImageView)view.findViewById(R.id.imageView1 );

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);

                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imgarray.size();
        }

        public Object getItem(int position) {
            return imgarray.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

//            int rowWidth = (mMetrics.widthPixels) / 3;

            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(400,400));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(1, 1, 1, 1);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgarray.get(position));
            return imageView;
        }
    }




}