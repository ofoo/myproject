package com.guoguo.ynz.dao.user;

import com.guoguo.ynz.entity.user.User;
import com.guoguo.ynz.entity.user.UserRolePermission;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> getUserList();

    /**
     * 查询所有用户、角色、权限
     * @return
     */
    List<UserRolePermission> getUserRolePermissionList();

}
