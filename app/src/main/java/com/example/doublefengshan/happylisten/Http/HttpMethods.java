package com.example.doublefengshan.happylisten.Http;


import android.content.Context;

import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by DoubleFengshan on 2017/8/27.
 */

public class HttpMethods {
    private static String MY_URL="http://fm.zhangyuzhuhai.com:8080/";
    public static void UpdateInfo(final String address, final String action, okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("action",action)
                .build();
        Request request=new Request.Builder()
                .url(MY_URL+address)
                 .post(body)
                 .build();
        client.newCall(request).enqueue(callback);
    }
    public static void downloadImage(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(MY_URL+address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
