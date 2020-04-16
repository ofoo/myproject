package com.guoguo.murongqing.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoguo.murongqing.dao.user.UserDao;
import com.guoguo.murongqing.entity.user.User;
import com.guoguo.murongqing.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public PageInfo<User> getUserList(User user, int pageNum, int pageSize) {
        user.setName(StringUtils.isBlank(user.getName()) ? null : user.getName());
        PageHelper.startPage(pageNum, pageSize, "id desc");
        List<User> list = userDao.getUserList(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateUserName(User user) {
        return userDao.updateUserName(user);
    }

    @Override
    public int updateUserImage(User user) {
        return userDao.updateUserImage(user);
    }

    @Override
    public int updateUserPwd(User user) {
        return userDao.updateUserPwd(user);
    }

    @Override
    public List<Long> getUserIdsByIdsAndCreateUserId(List<Long> userIds, Long createUserId) {
        return userDao.getUserIdsByIdsAndCreateUserId(userIds,createUserId);
    }

    @Override
    public int updateUserStatus(User user) {
        return userDao.updateUserStatus(user);
    }

    @Override
    public int getUserCountByIdAndCreateUserId(User userData) {
        return userDao.getUserCountByIdAndCreateUserId(userData);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public int updateUser(User userData) {
        return userDao.updateUser(userData);
    }

    @Override
    public User getUserByIdAndCreateUserId(User userData) {
        return userDao.getUserByIdAndCreateUserId(userData);
    }

    @Override
    public int getUserCountByName(User userData) {
        return userDao.getUserCountByName(userData);
    }
}
