package com.example.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.header.setText(model.getHeader());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);

        holder.img1.setOnClickListener(view -> {
            Intent intent=new Intent(holder.img1.getContext(),news_desc.class);
            intent.putExtra("linkvalue",model.getLink());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.img1.getContext().startActivity(intent);
            ((Activity)holder.img1.getContext()).finish();
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        ImageView img1;
        TextView header, link;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img1);
            header=itemView.findViewById(R.id.header);
            link=itemView.findViewById(R.id.link);
        }
    }




}
