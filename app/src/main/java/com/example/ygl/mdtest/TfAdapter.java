package com.example.ygl.mdtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ygl.mdtest.model.Tf;
import java.util.List;

/**
 * Created by YGL on 2017/3/15.
 */

public class TfAdapter extends RecyclerView.Adapter<TfAdapter.ViewHolder>{
    private Context mcontext;
    private List<Tf> mtfList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView tfView;
        TextView name;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            tfView=(ImageView)view.findViewById(R.id.tf_image);
            name=(TextView)view.findViewById(R.id.name);
        }
    }

    public TfAdapter(List<Tf> tfList){
        mtfList=tfList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.card_layout,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=viewHolder.getAdapterPosition();
                Tf tf=mtfList.get(i);
                Intent intent=new Intent(mcontext,ItemActivity.class);
                intent.putExtra(ItemActivity.name,tf.getName());
                intent.putExtra(ItemActivity.imageid,tf.getImageId());
                mcontext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Tf tf=mtfList.get(position);
        holder.name.setText(tf.getName());
        Glide.with(mcontext).load(tf.getImageId()).into(holder.tfView);
    }
   @Override
    public int getItemCount(){
       return mtfList.size();
   }
}
