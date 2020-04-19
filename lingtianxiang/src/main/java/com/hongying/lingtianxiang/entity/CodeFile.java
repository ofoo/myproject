package com.hongying.lingtianxiang.entity;

import lombok.Data;

@Data
public class CodeFile {
    private String name;
    private String content;
    private String type;

    public final static String ENTITY = "Entity";
    public final static String ENTITY_CONDITION = "EntityCondition";
    public final static String DAO = "Dao";
    public final static String BIZ = "Biz";
    public final static String CONTROLLER = "Controller";
    public final static String SERVICE = "Service";
}
