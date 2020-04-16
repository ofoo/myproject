package com.guoguo.ynz.dao.role;

import com.guoguo.ynz.entity.role.Role;

import java.util.List;

public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> getRoleList();
}
