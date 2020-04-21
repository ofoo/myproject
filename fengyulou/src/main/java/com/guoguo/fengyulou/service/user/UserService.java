package com.guoguo.fengyulou.service.user;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.user.User;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public interface UserService {
    /**
     * 用户登录验证
     * @param session
     * @param user
     * @return
     */
    ServerResponse login(HttpSession session, User user);
}
