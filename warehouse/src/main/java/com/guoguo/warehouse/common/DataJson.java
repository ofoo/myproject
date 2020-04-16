package com.guoguo.warehouse.common;

import lombok.Data;

@Data
public class DataJson {
    private Integer code;
    private String msg;
    private Long count;
    private Object data;

    public static DataJson list(String msg, Long count, Object data) {
        DataJson dataJson = new DataJson();
        dataJson.code = 0;
        dataJson.msg = msg;
        dataJson.count = count;
        dataJson.data = data;
        return dataJson;
    }

    public static DataJson list(Long count, Object data) {
        DataJson dataJson = new DataJson();
        dataJson.code = 0;
        dataJson.count = count;
        dataJson.data = data;
        return dataJson;
    }
}
