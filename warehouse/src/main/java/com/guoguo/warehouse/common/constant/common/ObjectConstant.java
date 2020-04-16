package com.guoguo.warehouse.common.constant.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 通用常量
 *
 * @author GC
 */
public class ObjectConstant {
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
}
