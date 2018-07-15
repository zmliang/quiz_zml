package com.self.java.quiz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static String currentDateStr = "";
    private static final long M_SECONDS_PER_MINUTE = 1000*60;
    private static final long M_SECONDS_PER_HOUR = 1000*60*60;
    private static final long M_SECONDS_PER_DAY = 1000*24*60*60;


    public static String getPreDay(Date date) {
        return getPreDay(date,-1);
    }

    public static String getPreDay(Date date,int count){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, count);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static String current(){
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
        return sdf.format(calendar.getTime());
    }

    public static boolean isEffectiveDate(Date nowdata) throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date nowTime = df.parse(df.format(nowdata));
        Date startTime = df.parse("00:00");
        Date endTime = df.parse("22:00");

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean sameDay(long startTime,long endTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date(startTime)).equals(df.format(new Date(endTime)));
    }


    public static boolean isExpired(long startTime,long endTime) throws Exception{
        if (!sameDay(startTime,endTime))
            return false;
        return  (isEffectiveDate(new Date(startTime)) && !isEffectiveDate(new Date(endTime)));
    }

    public static boolean isCurrent(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String current= df.format(new Date(System.currentTimeMillis()));
        if (currentDateStr.equals(current)){
            return true;
        }else {
            currentDateStr = current;
            return false;
        }
    }

}
