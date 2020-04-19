package com.hongying.lingtianxiang.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Code {
    private String explain;
    private String entity;
    private List<Map<String,String>> attrList;
}
