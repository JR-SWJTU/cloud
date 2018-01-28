package com.jr.cloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/27.
 */
public class DateUtile {

    public static String longToStringDate(long time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
    }
}
