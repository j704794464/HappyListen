package com.example.doublefengshan.happylisten;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.doublefengshan.happylisten.R;
import com.example.doublefengshan.happylisten.self_class.MyDatabaseHelper;
import com.example.doublefengshan.happylisten.self_class.Sound;

import org.w3c.dom.Text;

public class Player extends BaseActivity implements View.OnClickListener{
 //////////////////////////初始化各个控件///////////////
    private MediaPlayer mediaPlayer=new MediaPlayer();
    TextView player_time;
    Button player_start;
    Button pause;
    Button stop;
    SeekBar player_seekbar;
    Intent intent_start;
    private MyDatabaseHelper dbhelper;//定义数据库
    ////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();//初始化各个控件
        initMediaPlayer();
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.player_start:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
        }
    }
    protected void onDestroy(){
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    public void initView(){
        player_seekbar=(SeekBar)findViewById(R.id.player_seekbar);
        player_time=(TextView)findViewById(R.id.player_time);
        player_start=(Button)findViewById(R.id.player_start);
        pause=(Button)findViewById(R.id.pause);
        stop=(Button)findViewById(R.id.stop);
        intent_start=getIntent();//获取上个活动的信息
        player_start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
    public void initMediaPlayer(){
        try{
            Log.d("initMediaPlayer", "1 ");
            dbhelper=new MyDatabaseHelper(this,"Album.db",null,1);
            SQLiteDatabase db=dbhelper.getWritableDatabase();
            String soundName=intent_start.getStringExtra("SoundName");
            Log.d("initMediaPlayer", soundName);
            Cursor cursor=db.query("sounds",null,"name=?",new String[]{soundName},null,null,null);
            String loc ;
            cursor.moveToFirst();
            loc=cursor.getString(cursor.getColumnIndex("location"));
            Log.d("initMediaPlayer", "2 ");
            String sound_url="http://fm.zhangyuzhuhai.com:8080/"+loc;
            Log.d("initMediaPlayer", sound_url);
            mediaPlayer.setDataSource(sound_url);
            mediaPlayer.prepare();
            }catch (Exception e){
            e.printStackTrace();
            Log.d("initMediaPlayer", "error ");
        }
    }
}
