package com.example.doublefengshan.happylisten.self_class;

import android.graphics.Bitmap;

/**
 * Created by DoubleFengshan on 2017/8/26.
 */

public class album {
    private String name;
    private String num;
    private String imagePath;
    public album(String name,String num,String imagePath){
        this.name=name;
        this.num=num;
        this.imagePath=imagePath;
    }
    public String getName(){return name;}
    public String getNum(){return num;}
    public String getImagePath(){return imagePath;}
}
