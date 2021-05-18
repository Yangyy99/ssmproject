package com.ityang.ssm.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换的工具类
 */

public class DateUtils {
    /**
     * 日期->字符串
     */
    public static String date2String(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String format=sdf.format(date);
        return format;

    }
    /**
     * 字符串转换成 日期
     */
    public static Date string2Date(String str,String pattern){

        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date date=null;
        try {
             date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    return date;
    }
}
