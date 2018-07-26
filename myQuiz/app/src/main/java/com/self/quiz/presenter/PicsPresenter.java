package com.self.quiz.presenter;

import android.util.Log;

import com.self.quiz.modal.GankResult;
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
    private PicsCB picsCB;

    public PicsPresenter(IPicsView view){
        picsView = view;
        attachView(picsView);
        picsCB = new PicsCB(picsView);
    }
    static class PicsCB extends CallBack<GankResult> {
        private WeakReference<IPicsView> viewWeakReference;
        public PicsCB(IPicsView view){
            viewWeakReference = new WeakReference<IPicsView>(view);
        }
        @Override
        public void onSuccess(GankResult model) {
            viewWeakReference.get().loadPics(model.getResults());
        }

        @Override
        public void onFailed(String message) {
            Log.i(TAG,"Failed:"+message);
            viewWeakReference.get().onCancelDialog();
        }

        @Override
        public void onFinished() {
            viewWeakReference.get().onCancelDialog();
        }
    }

    @Override
    public void detachView(){
        super.detachView();
        picsCB = null;
    }

    public void getPics(int page){
        addSubscription(mApi.getVideo(CommonApi.GANK_URL_IMAGE+page),picsCB);
    }
}
