package com.guoguo.ynz.dao.role;

import com.guoguo.ynz.entity.role.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 */
public interface RoleDao {

    /**
     * 查询所有角色
     *
     * @param role
     * @return
     */
    List<Role> getRoleList(Role role);

    /**
     * 按id查询角色
     *
     * @param id
     * @return
     */
    Role getRoleById(@Param("id") Long id);

    /**
     * 按id删除角色
     *
     * @param roleList
     * @return
     */
    int deleteRoleByIds(List<Role> roleList);

    /**
     * 根据id修改角色
     *
     * @param role
     * @return
     */
    int updateRoleById(Role role);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 查询角色总数
     *
     * @param role
     * @return
     */
    Integer getRoleListCount(Role role);
}
