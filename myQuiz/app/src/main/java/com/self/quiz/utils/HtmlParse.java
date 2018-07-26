package com.self.quiz.utils;

import android.util.Log;

import com.self.quiz.modal.NewsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/26
 * CopyRight:  JinkeGroup
 */

public class HtmlParse {

    public static List<NewsItem> parseHtmlData(String result) {
        List<NewsItem> list = new ArrayList<>();

        Pattern pattern = Pattern
                .compile("<a target=\"_blank\" class=\"pic\" href=\"([^\"]*)\"><img class=\"picto\" src=\"([^\"]*)\"></a><em class=\"f14 l24\"><a target=\"_blank\" class=\"linkto\" href=\"[^\"]*\">([^</a>]*)</a></em><p class=\"l22\">([^</p>]*)</p>");
        Matcher matcher = pattern.matcher(result);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            NewsItem model = new NewsItem();
            model.setNewsDetailUrl(matcher.group(1).trim());
            model.setUrlImgAddress(matcher.group(2).trim());
            model.setNewsTitle(matcher.group(3).trim());
            model.setNewsSummary(matcher.group(4).trim());

            sb.append("详情页地址：" + matcher.group(1).trim() + "\n");
            sb.append("图片地址：" + matcher.group(2).trim() + "\n");
            sb.append("标题：" + matcher.group(3).trim() + "\n");
            sb.append("概要：" + matcher.group(4).trim() + "\n\n");

            list.add(model);
        }

        Log.e("----------------->", sb.toString());

        return list;
    }

}
