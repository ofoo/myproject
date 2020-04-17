package com.guoguo.ynz.entity.role;

import lombok.Data;

import java.util.Date;

/**
 * 角色
 */
@Data
public class Role {
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
