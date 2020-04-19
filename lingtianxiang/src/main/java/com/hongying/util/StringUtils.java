package com.hongying.util;

public class StringUtils {
    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String capFirst(String str){
        String temStr = str.substring(0,1).toUpperCase();
        return temStr+str.substring(1);
    }
}
