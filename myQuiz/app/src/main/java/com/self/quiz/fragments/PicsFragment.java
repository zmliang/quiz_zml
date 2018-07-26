package com.self.quiz.fragments;

import android.view.View;

import com.self.quiz.R;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class PicsFragment extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.pics_fragment;
    }

    @Override
    public void initView(View view) {

    }

    /*
    private RecyclerView videoRecyclerView;
    private NewsPresenter newsPresenter = new NewsPresenter(this);
    private NewsAdapter adapter;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        newsPresenter.getVideo(1);

    }

    @Override
    public int getLayoutID() {
        return R.layout.pics_fragment;
    }

    @Override
    public void initView(View view) {
        videoRecyclerView =view.findViewById(R.id.gank_pics_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        videoRecyclerView.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new NewsAdapter(getContext());
        adapter.setLayout(R.layout.pics_list_item);
    }

    @Override
    public void fetchData(List<GankItem> list) {
        adapter.setDatas(list);
        videoRecyclerView.setAdapter(adapter);
    }

    */
}
