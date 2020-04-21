package com.guoguo.ynz.dao.user;

import com.guoguo.ynz.entity.user.User;
import com.guoguo.ynz.entity.user.UserRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> getUserList(User user);

    /**
     * 查询所有用户、角色、权限
     * @return
     */
    List<UserRolePermission> getUserRolePermissionList();

    /**
     * 按id查询用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 按id删除用户
     * @return
     */
    int deleteUserByIds(List<User> userList);

    /**
     * 根据id修改用户
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 查询数据总数
     * @param user
     * @return
     */
    Integer getUserListCount(User user);

    /**
     * 按手机号查询
     * @param mobile
     * @return
     */
    int getUserCountByMobile(@Param("mobile") String mobile);
}
