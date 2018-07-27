package com.self.quiz.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.self.quiz.modal.GankResult;
import com.self.quiz.modal.JsonRootBean;
import com.self.quiz.utils.CallBack;
import com.self.quiz.utils.CommonApi;
import com.self.quiz.view.IPicsView;

import java.lang.ref.WeakReference;

/**
 * Created by zmliang on 2018/7/26.
 */

public class PicsPresenter extends BasePresenter<IPicsView> {
    private static final String TAG = PicsPresenter.class.getSimpleName();
    private IPicsView picsView;
    private final int COUNT = 10;

    public PicsPresenter(IPicsView view){
        picsView = view;
        attachView(picsView);

    }

    @Override
    public void detachView(){
        super.detachView();

    }

    private String buildUrlString(int page){
        return CommonApi.BAIDU_IMG_URL+"pn="+COUNT*page+"&rn="+COUNT+"&gsm=1e0&"+System.currentTimeMillis()+"=";
    }



    public void getPics(int page){
        final CallBack<JsonRootBean> suscriber = new CallBack<JsonRootBean>() {
            @Override
            public void onSuccess(JsonRootBean model) {
                Log.i(TAG,"pics result:"+model.getData().size());
                picsView.loadPics(model.getData());
            }

            @Override
            public void onFailed(String message) {
                picsView.failed();
            }

            @Override
            public void onFinished() {
                picsView.finished();
            }
        };

        addSubscription(mApi.getPics(buildUrlString(page)),suscriber);
    }
}
