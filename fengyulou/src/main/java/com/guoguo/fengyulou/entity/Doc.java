package com.guoguo.fengyulou.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Doc {
    private Long id;
    /**
     * 文档名称
     */
    private String name;
    /**
     * markdown文本
     */
    private String markdownText;
    /**
     * html文本
     */
    private String htmlText;
    /**
     * 创建人id
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
