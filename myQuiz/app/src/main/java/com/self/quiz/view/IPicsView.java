package com.self.quiz.view;

import com.self.quiz.modal.Data;
import com.self.quiz.modal.GankItem;

import java.util.List;

/**
 * Created by zmliang on 2018/7/26.
 */

public interface IPicsView extends BaseView{
    void loadPics(List<Data> list);
}
