package com.guoguo.ynz.entity.permission;

import lombok.Data;

import java.util.Date;

/**
 * 权限
 */
@Data
public class Permission {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 用户状态 0正常 2删除
     */
    private Integer status;
}
