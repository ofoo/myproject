package com.guoguo.ynz.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
public class User {
    private Long id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 用户状态 0正常 2删除
     */
    private Integer status;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 数据条数
     */
    private Integer limit;
}
