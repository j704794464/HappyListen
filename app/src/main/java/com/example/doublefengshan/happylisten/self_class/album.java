package com.example.doublefengshan.happylisten.self_class;

import android.graphics.Bitmap;

/**
 * Created by DoubleFengshan on 2017/8/26.
 */

public class album {
    private String name;
    private String num;
    private Bitmap imageID;
    public album(String name,String num,Bitmap imageID){
        this.name=name;
        this.num=num;
        this.imageID=imageID;
    }
    public String getName(){return name;}
    public String getNum(){return num;}
    public Bitmap getImageID(){return imageID;}
}
