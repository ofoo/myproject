package com.guoguo.warehouse.common;

import lombok.Data;

/**
 * @author GC
 */
@Data
public class ResultJson {
    private Boolean success;
    private String msg;
    private Object data;

    public static ResultJson result(boolean success, String msg, Object data) {
        ResultJson resultJson = new ResultJson();
        resultJson.success = success;
        resultJson.msg = msg;
        resultJson.data = data;
        return resultJson;
    }

    public static ResultJson result(boolean success, String msg) {
        ResultJson resultJson = new ResultJson();
        resultJson.success = success;
        resultJson.msg = msg;
        return resultJson;
    }

    public static ResultJson yes(String msg, Object data) {
        ResultJson resultJson = new ResultJson();
        resultJson.success = true;
        resultJson.msg = msg;
        resultJson.data = data;
        return resultJson;
    }

    public static ResultJson yes(String msg) {
        ResultJson resultJson = new ResultJson();
        resultJson.success = true;
        resultJson.msg = msg;
        return resultJson;
    }

    public static ResultJson yes() {
        ResultJson resultJson = new ResultJson();
        resultJson.success = true;
        return resultJson;
    }

    public static ResultJson no(String msg, Object data) {
        ResultJson resultJson = new ResultJson();
        resultJson.success = false;
        resultJson.msg = msg;
        resultJson.data = data;
        return resultJson;
    }

    public static ResultJson no(String msg) {
        ResultJson resultJson = new ResultJson();
        resultJson.success = false;
        resultJson.msg = msg;
        return resultJson;
    }

    public static ResultJson no() {
        ResultJson resultJson = new ResultJson();
        resultJson.success = false;
        return resultJson;
    }
}
