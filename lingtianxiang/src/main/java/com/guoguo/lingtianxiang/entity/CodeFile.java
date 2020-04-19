package com.guoguo.lingtianxiang.entity;

import lombok.Data;

@Data
public class CodeFile {
    private String name;
    private String content;
    private String type;

    public final static String ENTITY = "Entity";
    public final static String DAO = "Dao";
    public final static String SERVICE = "Service";
    public final static String CONTROLLER = "Controller";
    public final static String CONSTANT = "Constant";
    public final static String MAPPER = "Mapper";
    public static final String LIST = "list";
    public static final String SAVE = "save";
}
