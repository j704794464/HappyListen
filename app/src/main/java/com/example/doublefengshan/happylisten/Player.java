package com.example.doublefengshan.happylisten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.doublefengshan.happylisten.R;

import org.w3c.dom.Text;

public class Player extends BaseActivity {
 //////////////////////////初始化各个控件///////////////
    TextView player_time;
    Button player_start;
    Button pause;
    Button stop;
    SeekBar player_seekbar;
    ////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();//初始化各个控件
    }
    public void initView(){
        player_seekbar=(SeekBar)findViewById(R.id.player_seekbar);
        player_time=(TextView)findViewById(R.id.player_time);
        player_start=(Button)findViewById(R.id.player_start);
        pause=(Button)findViewById(R.id.pause);
        stop=(Button)findViewById(R.id.stop);
    }
}
