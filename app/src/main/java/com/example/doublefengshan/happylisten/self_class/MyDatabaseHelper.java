package com.example.doublefengshan.happylisten.self_class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DoubleFengshan on 2017/8/27.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_ALBUM="create table album("
            +"id integer primary key,"
            +"name text,"
            +"uper text,"
            +"location text,"
            +"time integer)";
    public static final String CREATE_SOUNDS="create table sounds("
            +"album_id integer primary key,"
            +"name text,"
            +"length text,"
            +"location text,"
            +"time integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_ALBUM);
        db.execSQL(CREATE_SOUNDS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //待写
    }
}
