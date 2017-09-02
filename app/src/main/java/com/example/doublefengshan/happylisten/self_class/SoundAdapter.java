package com.example.doublefengshan.happylisten.self_class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doublefengshan.happylisten.R;

import java.util.List;

/**
 * Created by DoubleFengshan on 2017/9/1.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {
    private List<Sound> mSoundList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView sound_name;
        TextView sound_length;
        public ViewHolder(View view){
            super(view);
            sound_name=(TextView)view.findViewById(R.id.sound_item_name);
            sound_length=(TextView)view.findViewById(R.id.sound_item_length);
        }
    }
    public SoundAdapter(List<Sound> soundList){
        mSoundList=soundList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sound_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        Sound sound=mSoundList.get(position);
        holder.sound_name.setText(sound.getName());
        holder.sound_length.setText(sound.getLength());
    }
    @Override
    public int getItemCount(){
        return mSoundList.size();
    }
}
