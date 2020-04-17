package com.guoguo.ynz.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
public class UserRolePermission {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 姓名
     */
    private String roleName;
    /**
     * 密码
     */
    private String permissionName;
}
