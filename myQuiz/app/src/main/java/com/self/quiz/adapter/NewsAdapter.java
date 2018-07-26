package com.self.quiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.self.quiz.R;
import com.self.quiz.modal.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsItem> datas = new ArrayList<>();
    private Context mContext;

    public NewsAdapter(Context context){
        mContext = context;
    }

    public void setDatas(List<NewsItem> dat){
        this.datas = dat;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.news_list_item,parent,false);
        return new ItemViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder){
            Glide.with(mContext).load(datas.get(position).getUrlImgAddress()).into(((ItemViewHolder) holder).imgView);
            ((ItemViewHolder) holder).title.setText(datas.get(position).getNewsTitle());
            ((ItemViewHolder) holder).summary.setText(datas.get(position).getNewsSummary());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
         ImageView imgView;
         TextView title;
         TextView summary;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.news_img);
            title = itemView.findViewById(R.id.news_title);
            summary = itemView.findViewById(R.id.news_summary);
        }
    }
}
