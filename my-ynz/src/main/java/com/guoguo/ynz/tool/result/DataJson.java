package com.guoguo.ynz.tool.result;

import lombok.Data;

@Data
public class DataJson {
    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public static DataJson list(Integer count, Object data) {
        DataJson dataJson = new DataJson();
        dataJson.code = 0;
        dataJson.count = count;
        dataJson.data = data;
        return dataJson;
    }
}
