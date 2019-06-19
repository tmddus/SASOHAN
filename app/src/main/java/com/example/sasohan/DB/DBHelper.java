package com.example.sasohan.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // db 새로 생성시 호출
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE familly (" + // 가족
                "    name TEXT NOT NULL PRIMARY KEY," + // 가족 이름
                "    phonenum  TEXT NOT NULL," +  // 가족 번호
                "    birth TEXT);"
        );  // 가족 생일


        db.execSQL("create table giukDate(" + // 기념일
                "    'date' text not null," + // 날짜
                "    'name' text not null," + // 기념일 이름
                "    'token' text not null PRIMARY KEY" + // 고유값(random string)
                ");"
        );

        db.execSQL("create table diary(" + // 일기
                "    'token' text not null PRIMARY KEY," + // 일기 고유값
                "    'title' text not null," + // 일기 제목
                "    'content' text not null," + // 일기 내용
                "    'writedate' text not null," + // 작성 날짜
                "    'receiver' text not null," + // 일기 받는 이
                "     'issended' INTEGER," + // 보낼지 안보낼지
                "     'isprivate' INTEGER," + // 나만 볼지
                "    'receivedate' text" + // 보내는 날짜
                ");"
        );


        db.execSQL("create table gallary(" + // 일기
                "    'token' text not null PRIMARY KEY," + // 일기 고유값
                "    'iamge' blob not null" + // 일기 제목
                ");"
        );



    }

    //버전 변경
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void DiaryInsert(String title, String content, String receiver, int isSend, int isPrivate){
        SQLiteDatabase db = getWritableDatabase();
        String token = rndString(10);


        db.execSQL("INSERT INTO diary (token, title, content, writedate, receiver, issended, isprivate, receivedate) values(" + token +
                    ", " + title + ", " + content + ", date('now')" + receiver + "," + isSend + "," +  isPrivate + ");"
                );


    }


    public void DateInsert(String date, String name){

        SQLiteDatabase db = getWritableDatabase();
        String token = rndString(10);

        db.execSQL("INSERT INTO diary (date, name) values(" +
                date + "," + name +
                ");"
        );
    }


    private String rndString(int len){
        Random rnd =new Random();

        StringBuffer buf =new StringBuffer();

        for(int i=0;i<len;i++) {

            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

            if (rnd.nextBoolean()) {

                buf.append((char) ((int) (rnd.nextInt(26)) + 97));

            } else {

                buf.append((rnd.nextInt(10)));
            }

        }

        return buf.toString();
    }


}
