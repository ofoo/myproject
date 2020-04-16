package com.guoguo.murongqing.dao.user;

import com.guoguo.murongqing.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User getUserByName(String name);

    int addUser(User user);

    List<User> getUserList(User user);

    int updateUserName(User user);

    int updateUserImage(User user);

    int updateUserPwd(User user);

    int updateUserStatus(User user);

    List<Long> getUserIdsByIdsAndCreateUserId(@Param("list") List<Long> userIds, @Param("createUserId") Long createUserId);

    int getUserCountByIdAndCreateUserId(User userData);

    User getUserById(Long id);

    int updateUser(User userData);

    User getUserByIdAndCreateUserId(User userData);

    int getUserCountByName(User userData);
}
