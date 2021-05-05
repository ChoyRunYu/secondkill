package com.secondkill.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author choy
 * @date 2021/03/13
 * 时间工具类
 */
public class TimeUtils {

    /**
     * 计算某个时间距离现在有多少秒
     * @param endDate
     * @return
     */
    public static Integer calSecond(Date endDate){
        long time1 = new Date().getTime();
        long time2 = endDate.getTime();
        return Integer.parseInt(Long.toString((time2 - time1) /1000 ));
    }

    /**
     * 计算某个时间字符串距离现在有多少秒
     * @param time
     * @return
     * @throws ParseException
     */
    public static Integer calSecondString(String time) throws ParseException {
        long time1 = new Date().getTime();
        long time2 = string2Date(time).getTime();
        return Integer.parseInt(Long.toString((time2 - time1) /1000 ));
    }

    /**
     * 字符从转date
     * @param time
     * @return
     */
    public static Date string2Date(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(time);
    }

    /**
     * data时间转string字符串
     * @param date
     * @return
     */
    public static String date2String(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
