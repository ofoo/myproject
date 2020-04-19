package com.guoguo.ynz.dao.permission;

import com.guoguo.ynz.entity.permission.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限
 */
public interface PermissionDao {

    /**
     * 查询所有权限
     *
     * @param permission
     * @return
     */
    List<Permission> getPermissionList(Permission permission);

    /**
     * 按id查询权限
     *
     * @param id
     * @return
     */
    Permission getPermissionById(@Param("id") Long id);

    /**
     * 按id删除权限
     *
     * @param permissionList
     * @return
     */
    int deletePermissionByIds(List<Permission> permissionList);

    /**
     * 根据id修改权限
     *
     * @param permission
     * @return
     */
    int updatePermissionById(Permission permission);

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    int addPermission(Permission permission);

    /**
     * 查询权限总数
     *
     * @param permission
     * @return
     */
    Integer getPermissionListCount(Permission permission);
}
