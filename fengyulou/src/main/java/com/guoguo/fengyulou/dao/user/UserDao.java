package com.guoguo.fengyulou.dao.user;

import com.guoguo.fengyulou.entity.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User getUserByLoginName(@Param("loginName") String loginName);
}
