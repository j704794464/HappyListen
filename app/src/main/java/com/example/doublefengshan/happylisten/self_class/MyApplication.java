package com.example.doublefengshan.happylisten.self_class;

import android.app.Application;
import android.content.Context;

/**
 * Created by DoubleFengshan on 2017/8/28.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
