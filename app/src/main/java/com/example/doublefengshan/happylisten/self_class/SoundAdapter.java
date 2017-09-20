package com.example.doublefengshan.happylisten.self_class;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doublefengshan.happylisten.Player;
import com.example.doublefengshan.happylisten.R;

import java.util.List;

/**
 * Created by DoubleFengshan on 2017/9/1.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {
    private List<Sound> mSoundList;
    private Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View soundView;
        TextView sound_name;
        TextView sound_length;
        public ViewHolder(View view){
            super(view);
            soundView=view;
            sound_name=(TextView)view.findViewById(R.id.sound_item_name);
            sound_length=(TextView)view.findViewById(R.id.sound_item_length);
        }
    }
    public SoundAdapter(List<Sound> soundList){
        mSoundList=soundList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext==null){
            mContext=parent.getContext();
        }
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sound_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.soundView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Sound sound=mSoundList.get(position);
                Intent intent=new Intent(mContext, Player.class);
                intent.putExtra("SoundName",sound.getName());
                intent.putExtra("SoundLength",sound.getLength());
                mContext.startActivity(intent);
            }
        });
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
