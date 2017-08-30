package com.example.doublefengshan.happylisten.self_class;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doublefengshan.happylisten.R;

import java.util.List;

/**
 * Created by DoubleFengshan on 2017/8/26.
 */

public class albumAdapter extends RecyclerView.Adapter<albumAdapter.ViewHolder> {
    private Context mContext;
    private List<album> malbumList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView albumImage;
        TextView albumName;
        TextView albumNum;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            albumImage=(ImageView) view.findViewById(R.id.album_image);
            albumName=(TextView) view.findViewById(R.id.album_name);
            albumNum=(TextView) view.findViewById(R.id.album_num);
        }
    }
    public albumAdapter(List<album> albumList){
        malbumList=albumList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.album,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        album malbum=malbumList.get(position);
        holder.albumName.setText(malbum.getName());
        holder.albumNum.setText(malbum.getNum());
        Glide.with(mContext).load(malbum.getImageID()).into(holder.albumImage);
    }
    @Override
    public int getItemCount(){
        return malbumList.size();
    }
}
