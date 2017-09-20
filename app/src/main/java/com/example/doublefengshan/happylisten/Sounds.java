package com.example.doublefengshan.happylisten;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doublefengshan.happylisten.self_class.DividerItemDecoration;
import com.example.doublefengshan.happylisten.self_class.MyApplication;
import com.example.doublefengshan.happylisten.self_class.MyDatabaseHelper;
import com.example.doublefengshan.happylisten.self_class.Sound;
import com.example.doublefengshan.happylisten.self_class.SoundAdapter;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Sounds extends BaseActivity {

    Intent intent_start;//用来获取上个活动信息
    ////////////////////////////////////////
    private RecyclerView recyclerView;//定义recycleview
    private List<Sound> SoundList;
    //////////////////////////////////////
    private MyDatabaseHelper dbhelper;//定义数据库
    /////////////////////////////////////
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
        intent_start=getIntent();//获取上个活动信息的intent
        SoundList=new ArrayList<>();//这是recycleview用到的list
        ///////////////////////////

        ///////////////////////////
        initBar();
        initRecycleView();
    }
    private void initBar(){
        String AlbumName=intent_start.getStringExtra("AlbumName");
        String SoundNum=intent_start.getStringExtra("SoundNum");
        String ImagePath=intent_start.getStringExtra("ImagePath");
        ImageView album_image=(ImageView)findViewById(R.id.sounds_image);
        TextView album_name=(TextView)findViewById(R.id.sounds_name);
        album_name.setText(AlbumName);
        Glide.with(MyApplication.getContext()).load(new File(ImagePath)).into(album_image);
    }
    private void initRecycleView(){
        for (int i=0;i<=10;i++){
            initSounds();
        }

        recyclerView=(RecyclerView)findViewById(R.id.sound_item_recycle);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SoundAdapter adapter=new SoundAdapter(SoundList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter);
    }
    private void initSounds(){
        dbhelper=new MyDatabaseHelper(this,"Album.db",null,1);
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        int album_id=intent_start.getIntExtra("AlbumId",0);
        Cursor cursor=db.query("sounds",null,"album_id=?",new String[]{String.valueOf(album_id)},null,null,null);
        if(cursor.moveToFirst()){
            do {
                if(album_id==cursor.getInt(cursor.getColumnIndex("album_id"))){
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String length=cursor.getString(cursor.getColumnIndex("length"));
                    SoundList.add(new Sound(name,length));
                }
            }while (cursor.moveToNext());
        }
    }
}
