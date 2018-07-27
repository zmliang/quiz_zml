package com.self.quiz.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.self.quiz.R;
import com.self.quiz.adapter.PicsAdapter;
import com.self.quiz.modal.Data;
import com.self.quiz.modal.GankItem;
import com.self.quiz.presenter.PicsPresenter;
import com.self.quiz.view.IPicsView;

import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class PicsFragment extends BaseFragment implements IPicsView{
    private final String TAG = PicsFragment.class.getSimpleName();

    private PicsPresenter picsPresenter = new PicsPresenter(this);
    private RecyclerView picsList;
    private PicsAdapter adapter;

    private int page = 1;

    @Override
    public int getLayoutID() {
        return R.layout.pics_fragment;
    }

    @Override
    public void initView(View view) {
        picsList =view.findViewById(R.id.gank_pics_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        picsList.setLayoutManager(manager);
        picsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager1 = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastPos = manager1.findLastCompletelyVisibleItemPosition();
                    int totalCount = manager1.getItemCount();
                    Log.i(TAG,"lastPos:"+lastPos+"; total:"+totalCount);
                    if (lastPos == totalCount-1 && isSlidingToLast){
                        picsPresenter.getPics(++page);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    isSlidingToLast = true;
                }else {
                    isSlidingToLast = false;
                }
            }
        });
        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new PicsAdapter(this.getContext());
        picsList.setAdapter(adapter);
        picsPresenter.getPics(page);
    }

    @Override
    public void onResume(){
        super.onResume();

    }



    @Override
    public void loadPics(List<Data> list) {
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
    }
}
