package com.example.doublefengshan.happylisten;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doublefengshan.happylisten.self_class.MyApplication;
import com.example.doublefengshan.happylisten.self_class.MyDatabaseHelper;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URL;

public class Sounds extends BaseActivity {

    private MyDatabaseHelper dbHelper;//加载数据库
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
        initBar();
    }
    private void initBar(){
        Intent intent_start=getIntent();
        intent_start=getIntent();
        String AlbumName=intent_start.getStringExtra("AlbumName");
        String SoundNum=intent_start.getStringExtra("SoundNum");
        String ImagePath=intent_start.getStringExtra("ImagePath");
        ImageView album_image=(ImageView)findViewById(R.id.sounds_image);
        TextView album_name=(TextView)findViewById(R.id.sounds_name);
        album_name.setText(AlbumName);
        Glide.with(MyApplication.getContext()).load(new File(ImagePath)).into(album_image);
    }
    private void loadSoundsList(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("album",null,"album_id=?",new String[]{/*等待补充album_id*/},null,null,null);
    }
}
