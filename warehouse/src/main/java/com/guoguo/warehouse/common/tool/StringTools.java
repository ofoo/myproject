package com.guoguo.warehouse.common.tool;

import jdk.nashorn.internal.ir.IdentNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author GC
 */
@Slf4j
public class StringTools {
    /**
     * 逗号分割字符串转Long类型List
     *
     * @param ids
     * @return
     */
    public static List<Long> strTurnListLong(String ids) {
        try {
            String[] split = ids.split(",");
            List<Long> longList = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                longList.add(Long.parseLong(split[i]));
            }
            return longList;
        } catch (Exception e) {
            log.error("(" + ids + ")转换异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 字符串装Long类型
     *
     * @param str
     * @return
     */
    public static Long strTurnLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            log.error("(" + str + ")转换异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 字符串数字相加
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String str1AddStr2(String str1, String str2) {
        try {
            int num1 = Integer.parseInt(str1);
            int num2 = Integer.parseInt(str2);
            return String.valueOf(num1 + num2);
        } catch (Exception e) {
            log.error("(" + str1 + "+" + str2 + ")转换异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 字符串数字相减
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String str1SubtractStr2(String str1, String str2) {
        try {
            int num1 = Integer.parseInt(str1);
            int num2 = Integer.parseInt(str2);
            return String.valueOf(num1 - num2);
        } catch (Exception e) {
            log.error("(" + str1 + "+" + str2 + ")转换异常：" + e.getMessage());
        }
        return null;
    }
}
