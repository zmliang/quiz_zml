package com.self.quiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.self.quiz.R;
import com.self.quiz.modal.Data;
import com.self.quiz.modal.GankItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmliang on 2018/7/26.
 */

public class PicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public final static int NORMAL = 1;
    public final static int FOOTER = 2;

    private List<Data> datas = new ArrayList<>();
    private Context mContext;

    public PicsAdapter(Context context){
        mContext = context;
    }

    public void setDatas(List<Data> dat){
        this.datas.addAll(dat);
    }

    @Override
    public int getItemViewType(int position){
        if (getItemCount() == position+1){
            return FOOTER;
        }else {
            return NORMAL;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER){
            return new PicsAdapter.FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.footer,parent,false));
        }else {
            return new PicsAdapter.ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.pics_list_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PicsAdapter.ItemViewHolder){
            Glide.with(mContext).load(datas.get(position).getThumbURL())
                    .asBitmap()
                    .into(((PicsAdapter.ItemViewHolder) holder).imgView);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_item);
        }
    }
    static class FooterViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar footer;
        public FooterViewHolder(View itemView) {
            super(itemView);
            footer = itemView.findViewById(R.id.progressBar);
        }
    }
}
