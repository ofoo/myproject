package com.guoguo.ynz.dao.permission;

import com.guoguo.ynz.entity.permission.Permission;

import java.util.List;

public interface PermissionDao {
    /**
     * 查询所有权限
     * @return
     */
    List<Permission> getPermissionList();
}
