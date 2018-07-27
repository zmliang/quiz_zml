package com.self.quiz.view;

import com.self.quiz.modal.NewsItem;
import java.util.List;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public interface INewsView extends BaseView{

    void fetchData(List<NewsItem> list);
    void failed();
    void finished();

}
