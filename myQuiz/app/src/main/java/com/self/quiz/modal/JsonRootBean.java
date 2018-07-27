package com.self.quiz.modal;

import java.util.List;

/**
 * Auto-generated: 2018-07-27 13:48:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String queryEnc;
    private String queryExt;
    private int listNum;
    private int displayNum;
    private String gsm;
    private String bdFmtDispNum;
    private String bdSearchTime;
    private int isNeedAsyncRequest;
    private String bdIsClustered;
    private List<Data> data;
    public void setQueryEnc(String queryEnc) {
         this.queryEnc = queryEnc;
     }
     public String getQueryEnc() {
         return queryEnc;
     }

    public void setQueryExt(String queryExt) {
         this.queryExt = queryExt;
     }
     public String getQueryExt() {
         return queryExt;
     }

    public void setListNum(int listNum) {
         this.listNum = listNum;
     }
     public int getListNum() {
         return listNum;
     }

    public void setDisplayNum(int displayNum) {
         this.displayNum = displayNum;
     }
     public int getDisplayNum() {
         return displayNum;
     }

    public void setGsm(String gsm) {
         this.gsm = gsm;
     }
     public String getGsm() {
         return gsm;
     }

    public void setBdFmtDispNum(String bdFmtDispNum) {
         this.bdFmtDispNum = bdFmtDispNum;
     }
     public String getBdFmtDispNum() {
         return bdFmtDispNum;
     }

    public void setBdSearchTime(String bdSearchTime) {
         this.bdSearchTime = bdSearchTime;
     }
     public String getBdSearchTime() {
         return bdSearchTime;
     }

    public void setIsNeedAsyncRequest(int isNeedAsyncRequest) {
         this.isNeedAsyncRequest = isNeedAsyncRequest;
     }
     public int getIsNeedAsyncRequest() {
         return isNeedAsyncRequest;
     }

    public void setBdIsClustered(String bdIsClustered) {
         this.bdIsClustered = bdIsClustered;
     }
     public String getBdIsClustered() {
         return bdIsClustered;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    @Override
    public String toString() {
        return "JsonRootBean{" +
                "queryEnc='" + queryEnc + '\'' +
                ", queryExt='" + queryExt + '\'' +
                ", listNum=" + listNum +
                ", displayNum=" + displayNum +
                ", gsm='" + gsm + '\'' +
                ", bdFmtDispNum='" + bdFmtDispNum + '\'' +
                ", bdSearchTime='" + bdSearchTime + '\'' +
                ", isNeedAsyncRequest=" + isNeedAsyncRequest +
                ", bdIsClustered='" + bdIsClustered + '\'' +
                ", data=" + data +
                '}';
    }
}