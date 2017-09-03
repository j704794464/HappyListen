package com.example.doublefengshan.happylisten.self_class;

import android.graphics.Bitmap;

/**
 * Created by DoubleFengshan on 2017/8/26.
 */

public class album {
    private String name;
    private String num;
    private String imagePath;
    private int album_id;
    public album(String name,String num,String imagePath,int album_id){
        this.name=name;
        this.num=num;
        this.imagePath=imagePath;
        this.album_id=album_id;
    }
    public String getName(){return name;}
    public String getNum(){return num;}
    public String getImagePath(){return imagePath;}
    public int getAlbum_id(){return album_id;}
}
