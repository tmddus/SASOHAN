package com.example.sasohan.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sasohan.Model.FamilyModel;
import com.example.sasohan.Model.giukDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // db 새로 생성시 호출
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE family (" + // 가족 테이블
                "    name TEXT PRIMARY KEY," + // 가족 이름
                "    phonenum  TEXT NOT NULL UNIQUE);" // 가족 번호
        );


        db.execSQL("create table giukDate(" + // 기념일 테이블
                "    'date' INTEGER not null," + // 날짜
                "    'name' text not null," + // 기념일 이름
                "    'token' text not null PRIMARY KEY" + // 고유값(random string)
                ");"
        );

        db.execSQL("create table diary(" + // 일기
                "    'token' TEXT not null PRIMARY KEY," + // 일기 고유값
                "    'title' TEXT not null," + // 일기 제목
                "    'content' TEXT not null," + // 일기 내용
                "    'writedate' INTEGER not null," + // 작성 날짜
                "    'receiver' TEXT not null," + // 일기 받는 이
                "     'issended' INTEGER," + // 보낼지 안보낼지
                "     'isprivate' INTEGER," + // 나만 볼지
                "    'receivedate' INTEGER" + // 보내는 날짜
                ");"
        );

        db.execSQL("create table gallary(" + // 갤러리 이미지
                "    'token' TEXT not null PRIMARY KEY," + // 사진 고유값
                "    'image' BLOB not null" + // 사진 binary 파일
                ");"
        );

    }

    //버전 변경
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void DiaryInsert(String title, String content, String receiver, int isSend, int isPrivate){ // 일기 저장
        SQLiteDatabase db = getWritableDatabase();
        String token = rndString(10);
        Date today = new Date();



        db.execSQL("INSERT INTO diary (token, title, content, writedate, " +
                        "receiver, issended, isprivate, receivedate) values(" + token +
                    ", " + title + ", " + content + ","+today.getTime() + receiver + "," + isSend + "," +  isPrivate + ");"
                );
    }


    public void DateInsert(long date, String name){

        SQLiteDatabase db = getWritableDatabase();
        String token = rndString(10);

        db.execSQL("INSERT INTO diary (token, name) values(" +
                date + "," + name +
                ");"
        );
    }

    public void familyInsert(String name, String phoneNum){ // 연락처 저장
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO family (date, name) values(" +
                name + "," + phoneNum + ");"
        );
    }

    public void imageInsert(byte[] img){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO gallary (date, image) values(" +
                rndString(10) + "," + img + ");"
        );
    }

    public ArrayList<FamilyModel> getAllFamily(){ // 연락처 모두 가져오기
        ArrayList<FamilyModel> arr= new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        FamilyModel model;

        Cursor cursor = db.rawQuery("SELECT * FROM family", null);

        while (cursor.moveToNext()){
            model = new FamilyModel(cursor.getString(0), cursor.getString(1));
            arr.add(model);
        }
        return arr;
    }

    public String getPhoneNum(String name){ // 이름 주면 번호 가져오기
        String result="error";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT phonenum FROM family WHERE name = " + name, null);

        while (cursor.moveToNext()){
            result = cursor.getString(0);
        }
        return result; // 행이 존재하지 않으면 error 를 반환
    }

    public ArrayList<giukDateModel> getAllGiukDate(){
        ArrayList<giukDateModel> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("  SELECT * FROM giukDate", null);
        while (cursor.moveToNext()){
            arr.add(new giukDateModel(cursor.getLong(0), cursor.getString(1)));
        }
        return arr;
    }

    public ArrayList<String> getGiukDate(long date){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM giukDate WHERE date = " + date, null);

        while (cursor.moveToNext()){
            arr.add(cursor.getString(0));
        }
        return arr;
    }

    public ArrayList<byte[]> getImage(){
        ArrayList<byte[]> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT image FROM gallary", null);

        while (cursor.moveToNext()){
            arr.add(cursor.getBlob(0));
        }

        return arr;

    }




    private String rndString(int len){
        Random rnd =new Random();

        StringBuffer buf =new StringBuffer();

        for(int i=0;i<len;i++) {
            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를,
            // false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

            if (rnd.nextBoolean()) {
                buf.append((char) ((int) (rnd.nextInt(26)) + 97));
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }

        return buf.toString();
    }


}
