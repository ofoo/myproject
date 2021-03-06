package com.guoguo.fengyulou.controller.user;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.controller.BaseController;
import com.guoguo.fengyulou.entity.user.User;
import com.guoguo.fengyulou.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/fyl")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/ajaxLogin")
    @ResponseBody
    public ServerResponse ajaxLogin(HttpSession session, User user) {
        return userService.login(session, user);
    }

    /**
     * 用户退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    /**
     * 修改用户密码
     *
     * @param session
     * @param pwd
     * @return
     */
    @RequestMapping("/user/ajaxUpdatePwd")
    @ResponseBody
    public ServerResponse ajaxUpdatePwd(HttpSession session, @RequestParam String pwd) {
        ServerResponse serverResponse = userService.updatePasswordById(session, pwd);
        if (serverResponse.isSuccess()) {
            session.removeAttribute("loginUser");
        }
        return serverResponse;
    }
}
