package com.guoguo.fengyulou.service.impl.user;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.dao.user.UserDao;
import com.guoguo.fengyulou.entity.user.User;
import com.guoguo.fengyulou.service.user.UserService;
import com.guoguo.util.MD5Util;
import com.guoguo.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ServerResponse login(HttpSession session, User user) {
        User tem = userDao.getUserByLoginName(user.getLoginName().trim());
        if (ObjectUtils.isNotNull(tem)) {
            user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
            if (tem.getPassword().equals(user.getPassword())) {
                session.setAttribute("loginUser", tem);
                return ServerResponse.createBySuccessMessage("登录成功");
            }
        }
        return ServerResponse.createByErrorMessage("账号或密码错误");
    }

    @Override
    public ServerResponse updatePasswordById(HttpSession session, String password) {
        User user = (User) session.getAttribute("loginUser");
        user.setPassword(MD5Util.MD5EncodeUtf8(password));
        int rows = userDao.updatePasswordById(user);
        if (rows > 0) {
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }
}
