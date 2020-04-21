package com.guoguo.lingtianxiang.entity;

import lombok.Data;

@Data
public class Code {
    /**
     * code文件路径
     */
    private String codeFilePath;
    /**
     * mppaer文件路径
     */
    private String mapperFilePath;
    /**
     * page文件路径
     */
    private String pageFilePath;
    /**
     * 请求根路径
     */
    private String requestRootUrl;
    /**
     * 包名称
     */
    private String packName;
    /**
     * 类描述
     */
    private String explain;
    /**
     * 类名称
     */
    private String entity;
    /**
     * 类属性（类型|名称|描述）
     */
    private String[] attr;
    /**
     * 数据库表
     */
    private String table;
}
