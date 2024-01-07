package com.example.astranews.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.astranews.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.astranews.dataclasses.JsonClass;
import com.squareup.picasso.Picasso;


public class RecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder>{

    JsonClass[] items;
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(com.example.astranews.R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        JsonClass current = items[position];
        holder.title.setText(current.newsTitle);
        holder.summary.setText(current.newsSummary);
        Picasso.get().load(current.imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView title;
    public TextView summary;
    View view;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(com.example.astranews.R.id.NewsImage);
        title = (TextView) itemView.findViewById(com.example.astranews.R.id.NewsTitle);
        summary = (TextView) itemView.findViewById(com.example.astranews.R.id.NewsSummary);
        view = itemView;
    }
}
