package com.sahil.newscenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private  Context parentContext;
    ArrayList<NewsObj> newsItems;
    LayoutInflater inflater;



    public NewsAdapter (Context context, ArrayList<NewsObj> newsItems){
        this.parentContext = context;
        this.newsItems = newsItems;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.news_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewsObj currentNews = newsItems.get(position);

        if(currentNews.getTitle() != null && !currentNews.getTitle().equals("")){
            holder.newsTitle.setText(currentNews.getTitle());
        }else {
            holder.newsTitle.setText("Unknown title...");
        }

        if(currentNews.getAuthor() != null && !currentNews.getAuthor().equals("")) {
            String sortAuthor = currentNews.getAuthor().length() > 20 ? currentNews.getAuthor().substring(0, 20) + "..." : currentNews.getAuthor();
            holder.newsAuthor.setText(sortAuthor);
        }else{
            holder.newsAuthor.setText("Unknown author...");
        }

        if(currentNews.getPublishDate() != null && !currentNews.getPublishDate().equals("")) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            try {
                Date date = format.parse(currentNews.getPublishDate());
                DateFormat format2 = new SimpleDateFormat("EEE,dd-MMM-yyyy", Locale.ENGLISH);
                if(date != null) {
                    String newStr = format2.format(date);
                    holder.newsPub.setText(newStr);
                }else {
                    holder.newsPub.setText("Unknown status...");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            holder.newsPub.setText("Unknown status...");
        }

        if(currentNews.getDescription() != null && !currentNews.getDescription().equals("")) {
            String shortDes = currentNews.getDescription().length() > 35 ?
                    currentNews.getDescription().substring(0, 35) : currentNews.getDescription();
            String des = shortDes + "...";
            holder.newsDes.setText(des);
        }else {
            holder.newsDes.setText("Description not available...");
        }

        if(currentNews.getImgUrl() != null && !currentNews.getImgUrl().equals("")) {
            Glide.with(parentContext).load(currentNews.getImgUrl()).error(R.drawable.no_image).into(holder.newsImg);
        }
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parentContext,NewsWebView.class);
                Bundle bundle = new Bundle();
                bundle.putString("newsTitle",currentNews.getTitle());
                bundle.putString("webUrl",currentNews.getWebUrl());
                intent.putExtras(bundle);
                parentContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle, newsDes, newsAuthor, newsPub;
        ImageView newsImg;
        Button readMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            newsDes = (TextView) itemView.findViewById(R.id.newsDes);
            newsAuthor = (TextView) itemView.findViewById(R.id.newsAuthor);
            newsPub = (TextView) itemView.findViewById(R.id.newsPub);
            newsImg = (ImageView) itemView.findViewById(R.id.newsImg);
            readMore = (Button) itemView.findViewById(R.id.readMore);

        }
    }
    /*
    public String stringDate (String str){
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try
        {
            date = form.parse("2011-03-27T09:39:01.607");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMMM dd, yyyy");
        String newDateStr = postFormater.format(date);
        return  newDateStr;
    }
     */

}



