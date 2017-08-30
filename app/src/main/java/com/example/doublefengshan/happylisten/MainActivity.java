package com.example.doublefengshan.happylisten;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.doublefengshan.happylisten.Http.HttpMethods;
import com.example.doublefengshan.happylisten.self_class.MyApplication;
import com.example.doublefengshan.happylisten.self_class.MyDatabaseHelper;
import com.example.doublefengshan.happylisten.self_class.album;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private static final int UPDATE_CARDVIEW=1;

    private DrawerLayout mDrawerlayout;//定义全局的滑动布局变量
    MyDatabaseHelper dbHelper;//初始化数据库
    ////////////////
    RecyclerView recyclerView;//初始化recycleview
    com.example.doublefengshan.happylisten.self_class.albumAdapter adapter;
    List<com.example.doublefengshan.happylisten.self_class.album> albums=new ArrayList<>();
    //////////////////
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.back:
                break;
            case R.id.delete:
                updateCardview();
                break;
            case R.id.settings:
                break;
            case android.R.id.home:
                mDrawerlayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_CARDVIEW:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onFirstOpen();//第一次打开软件时做的事情
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_main);//定义tootbar
        setSupportActionBar(toolbar);//加载toolbar

        /////////////////////
        mDrawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);//加home键位
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        ////////////////////////// //////////////////////
        recyclerView=(RecyclerView)findViewById(R.id.recycle);//初始化recycleview
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter =new com.example.doublefengshan.happylisten.self_class.albumAdapter(albums);
        recyclerView.setAdapter(adapter);
        updateCardview();//更新主界面卡片
        ///////////////////////////////////
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.float_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "fudong");//添加浮动按钮点击事件
            }
        });
        ///////////////////////////////

    }
    private void updateCardview(){
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(MyApplication.getContext(),"Album.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("album",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String uper=cursor.getString(cursor.getColumnIndex("uper"));
                String location=cursor.getString(cursor.getColumnIndex("location"));
                int time=cursor.getInt(cursor.getColumnIndex("time"));
                String s_id=String.valueOf(id);
                Log.d("initCard", location);
                Cursor cusor_sound=db.query("sounds",null,"album_id=?",new String[]{s_id},null,null,null);
                int sound_num=0;
                if(cusor_sound.moveToFirst()){
                    do {
                        sound_num++;
                    }while(cusor_sound.moveToNext());
                }
                //从本地读取图片
                String path=location;
                Log.d("updateCard", location);
                Bitmap album_image=getDiskBitmap(path);
                albums.add(new album(name,String.valueOf(sound_num)+"场表演",album_image));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_CARDVIEW;
                        handler.sendMessage(message);
                    }
                }).start();
            }while(cursor.moveToNext());
        }
        /*for(int i=1;i<=10;i++) {
            albums.add(new com.example.doublefengshan.happylisten.self_class.album(name, num+"场表演", R.drawable.wfs2));
        }

        recyclerView.setAdapter(adapter);*/
    }
    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try
        {
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e)
        {
        }
        return bitmap;
    }
    public static void openActivity(Context context){
        Intent intent=new Intent(context,Sounds.class);
        context.startActivity(intent);
    }
    private void setDatabase(){
        MyDatabaseHelper dbHelper;
        dbHelper=new MyDatabaseHelper(this,"Album.db",null,1);
        dbHelper.getWritableDatabase();
        SharedPreferences.Editor editor=getSharedPreferences("First",MODE_PRIVATE).edit();
        editor.putInt("isFirstOpen",0);
        editor.apply();
    }
    private void updateAlbums( String address,String action){
        HttpMethods.UpdateInfo(address,action,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response)throws IOException{
                String responseData=response.body().string();
                MyDatabaseHelper dbHelper=new MyDatabaseHelper(MyApplication.getContext(),"Album.db",null,1);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                try{
                    JSONArray jsonArray=new JSONArray(responseData);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int id=jsonObject.getInt("id");
                        String name=jsonObject.getString("name");
                        String uper=jsonObject.getString("uper");
                        final String location=jsonObject.getString("location");
                        int time=jsonObject.getInt("time");
                        Log.d("updateAlbum", name);
                        Log.d("updateAlbum", location);
                        HttpMethods.downloadImage(location, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                byte[] album_image=response.body().bytes();
                                Bitmap bitmap= BitmapFactory.decodeByteArray(album_image,0,album_image.length);
                                String local_location=saveImage(bitmap,location);
                                Log.d("updateAlbum", local_location);
                            }
                        });
                        values.put("id",id);
                        values.put("name",name);
                        values.put("uper",uper);
                        values.put("location",getDiskCacheDir(MyApplication.getContext())+"/"+location);
                        values.put("time",time);
                        db.insert("album",null,values);
                        values.clear();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call call, IOException e){
            }
        });
    }
    private void updateSounds(String address,String action){
        HttpMethods.UpdateInfo(address, action, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                MyDatabaseHelper dbHelper=new MyDatabaseHelper(MyApplication.getContext(),"Album.db",null,1);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                try{
                    JSONArray jsonArray=new JSONArray(responseData);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int id=jsonObject.getInt("id");
                        String name=jsonObject.getString("name");
                        String length=jsonObject.getString("length");
                        int time=jsonObject.getInt("time");
                        values.put("id",id);
                        values.put("name",name);
                        values.put("length",length);
                        values.put("time",time);
                        db.insert("sounds",null,values);
                        values.clear();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private String saveImage(Bitmap bitmap,String Filename){
        String path=getDiskCacheDir(MyApplication.getContext())+"/";
        File album_image = new File(path);
        if (!album_image.exists()) {
            album_image.mkdirs();
        }
        String image_path=path + Filename;
        File myCaptureFile = new File(image_path);
        try{
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            if(bitmap.isRecycled()==false)
            {
                bitmap.recycle();
            }
        }catch(Exception e){

        }
        return  image_path;
    }
    private String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
    private void onFirstOpen(){
        SharedPreferences pref=getSharedPreferences("First",MODE_PRIVATE);
        int isFirstOpen=pref.getInt("isFirstOpen",1);
        Log.d("data", String.valueOf(isFirstOpen));
        if(isFirstOpen==1){
            makeDir("album_image");
            setDatabase();
            updateAlbums("getAlbum.php","getAlbum");
            updateSounds("getSound.php","getSound");
        }
    }
    private void makeDir(String path){
        String path1=getDiskCacheDir(MyApplication.getContext())+"/"+path;
        Log.d("ma", path1);
        File dir = new File(path1);
        dir.mkdirs();
    }
    private void onOpen(){

    }
}
