package com.guoguo.murongqing.entity.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户密码
     */
    private String pwd;
    /**
     * 用户头像
     */
    private String image;
    /**
     * 用户职位
     */
    private String position;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
