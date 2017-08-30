package com.example.doublefengshan.happylisten.Http;

/**
 * Created by DoubleFengshan on 2017/8/28.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}