package com.guoguo.warehouse.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.function.UserConstant;
import com.guoguo.warehouse.common.tool.WebTools;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.entity.user.User;
import com.guoguo.warehouse.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author GC
 */
@Controller
@RequestMapping("/warehouse/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "user/";
        return pagePackage + pageName;
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return getPagePath("login");
    }

    /**
     * 登录操作
     *
     * @param user
     * @return
     */
    @RequestMapping("/ajax/login")
    @ResponseBody
    public ResultJson ajaxLogin(User user, HttpSession session, HttpServletRequest request) {
        if (StringUtils.isBlank(user.getName())) {
            return ResultJson.no("请输入账号");
        }
        if (StringUtils.isBlank(user.getPwd())) {
            return ResultJson.no("请输入密码");
        }
        ResultJson resultJson = userService.verifyUser(user, WebTools.getIpAddr(request));
        if (resultJson.getSuccess()) {
            session.setAttribute(UserConstant.CURRENT_USER, resultJson.getData());
        }
        return resultJson;
    }

    /**
     * 退出操作
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String ajaxLogout(HttpSession session) {
        session.removeAttribute(UserConstant.CURRENT_USER);
        return "redirect:/";
    }
}
