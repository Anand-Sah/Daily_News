package com.example.dailynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class News_AdapterClass extends RecyclerView.Adapter<News_AdapterClass.ViewHolder> {

    Context context;
    ArrayList<Item_GetterSetter> newsList;
    GetClickedPosition getClickedPosition;

    interface GetClickedPosition{
        void getPos(int pos);
    }

    public void OnCardViewClicked(GetClickedPosition getClickedPosition){
        this.getClickedPosition = getClickedPosition;
    }

    public News_AdapterClass(Context context, ArrayList<Item_GetterSetter> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item_GetterSetter item = newsList.get(position);

        Picasso.get().load(item.getUrlToImage()).placeholder(R.drawable.no_image_vector).fit().into(holder.thumbnail);
        holder.newsTitle.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.date.setText("  Date : "+item.getPublishedDate());
        holder.time.setText("   Time : "+item.getPublishedTime());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView thumbnail;
        public TextView newsTitle, description, date, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    getClickedPosition.getPos(pos);
                }
            });
        }
    }
}
