package com.guoguo.fengyulou.service;

import com.github.pagehelper.PageInfo;
import com.guoguo.fengyulou.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名称查询
     * *
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 创建用户
     *
     * @param userData
     * @return
     */
    int addUser(User userData);

    /**
     * 用户列表
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<User> getUserList(User user, int pageNum, int pageSize);

    /**
     * 修改用户名称
     *
     * @param user
     * @return
     */
    int updateUserName(User user);

    /**
     * 修改用户头像
     *
     * @param user
     * @return
     */
    int updateUserImage(User user);

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     */
    int updateUserPwd(User user);

    /**
     * 过滤掉无权限的用户id
     *
     * @param userIds
     * @param createUserId
     * @return
     */
    List<Long> getUserIdsByIdsAndCreateUserId(List<Long> userIds, Long createUserId);

    /**
     * 修改用户状态
     *
     * @param user
     * @return
     */
    int updateUserStatus(User user);

    /**
     * 过滤掉无权限的用户
     *
     * @param userData
     * @return
     */
    int getUserCountByIdAndCreateUserId(User userData);

    /**
     * 根据用户id查询
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 修改用户
     *
     * @param userData
     * @return
     */
    int updateUser(User userData);

    /**
     * 根据用户id和创建人id查询
     *
     * @param userData
     * @return
     */
    User getUserByIdAndCreateUserId(User userData);

    /**
     * 根据用户名称查询
     * @param userData
     * @return
     */
    int getUserCountByName(User userData);
}
