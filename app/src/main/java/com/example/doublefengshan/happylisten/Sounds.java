package com.example.doublefengshan.happylisten;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doublefengshan.happylisten.self_class.MyDatabaseHelper;

import java.net.URL;

public class Sounds extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;//加载数据库
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);
    }
    private void loadSounds(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Sounds",null);
    }
}
