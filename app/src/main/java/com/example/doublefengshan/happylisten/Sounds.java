package com.example.doublefengshan.happylisten;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doublefengshan.happylisten.self_class.MyDatabaseHelper;

import java.net.URL;

public class Sounds extends BaseActivity {

    private MyDatabaseHelper dbHelper;//加载数据库
    /////////////////////////////////////////
    private Intent intent_start;//带来音频数据
    ////////////////////////////////////////
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);
    }
    private void initBar(){
        intent_start=getIntent();
        String AlbumName=intent_start.getStringExtra("AlbumName");
        String SoundNum=intent_start.getStringExtra("SoundNum");
        String ImagePath=intent_start.getStringExtra("ImagePath");
    }
    private void loadSoundsList(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("album",null,"album_id=?",new String[]{/*等待补充album_id*/},null,null,null);
    }
}
