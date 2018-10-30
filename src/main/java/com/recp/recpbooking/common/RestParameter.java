package com.recp.recpbooking.common;

public class RestParameter {


    private static String restUserName = "aia";

    private static String restPassword = "123456";

    public static String getRestUserName() {
        return restUserName;
    }

    public static void setRestUserName(String restUserName) {
        RestParameter.restUserName = "aia";//restUserName;
    }

    public static String getRestPassword() {
        return restPassword;
    }

    public static void setRestPassword(String restPassword) {
        RestParameter.restPassword = "123456";//restPassword;
    }
}
