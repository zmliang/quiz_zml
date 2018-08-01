package com.self.quiz.activity;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.self.quiz.R;
import com.self.quiz.components.BaseActivity;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/31
 * CopyRight:  JinkeGroup
 */

public class WebActivity extends BaseActivity {
    private static final String TAG = WebActivity.class.getSimpleName();
    private WebView webView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_web;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;
            }
        });
    }
}




















