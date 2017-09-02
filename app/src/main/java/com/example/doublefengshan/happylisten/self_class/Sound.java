package com.example.doublefengshan.happylisten.self_class;

/**
 * Created by DoubleFengshan on 2017/9/1.
 */

public class Sound {
    private String name;
    private String length;
    public Sound(String name,String length){
        this.name=name;
        this.length=length;
    }
    public String getName(){return name;}
    public String getLength(){return length;}
}