package com.recp.recpbooking.common;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Utility {

    private static String javaDate(String fmt) {
        String dd;
        TimeZone gmt530 = TimeZone.getTimeZone("GMT");
        gmt530.setRawOffset((11 * 30) * 60 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        formatter.setTimeZone(gmt530);
        dd = formatter.format(new java.util.Date());
        return dd;
    }
}
