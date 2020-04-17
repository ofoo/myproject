package com.guoguo.murongqing.controller.user;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.Const;
import com.guoguo.common.ServerResponse;
import com.guoguo.config.MurongqingProperty;
import com.guoguo.murongqing.controller.common.BaseController;
import com.guoguo.murongqing.entity.user.User;
import com.guoguo.murongqing.service.user.UserService;
import com.guoguo.util.MD5Util;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.RedisCache;
import com.guoguo.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

import static com.guoguo.util.CookieUtils.setCookie;

@Controller
@RequestMapping("/admin/user")
@Slf4j
public class AdminUserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MurongqingProperty murongqingProperty;

    /**
     * 用户列表页面
     *
     * @param request
     * @param session
     * @param userData
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpSession session, User userData, @RequestParam(required = false, defaultValue = "1") int pageNum, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return NO_LOGIN;
        }
        // 执行查询操作
        PageInfo<User> pageInfo = userService.getUserList(userData, pageNum, pageSize);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("user", user);
        return "/admin-user-list";
    }

    /**
     * 修改用户页面
     *
     * @return
     */
    @RequestMapping("/save")
    public String save(Model model, HttpSession session) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return NO_LOGIN;
        }
        // 封装用户信息
        model.addAttribute("user", user);
        return "admin-user-save";
    }

    /**
     * 修改用户
     *
     * @param userData
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(@CookieValue String uuid, HttpSession session, User userData) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return ServerResponse.createByErrorCodeMessage(Const.User.NO_LOGIN, "用户没有登录");
        }
        // 检查数据是否正确
        if (StringUtils.isBlank(userData.getName())) {
            return ServerResponse.createByErrorMessage("请输入名称");
        }
        // 检查用户名是否存在
        if (!user.getName().equals(userData.getName())) {
            int count = userService.getUserCountByName(userData);
            if (count > 0) {
                return ServerResponse.createByErrorMessage("名称已存在");
            }
        }
        if (StringUtils.isBlank(userData.getPosition())) {
            return ServerResponse.createByErrorMessage("请输入职位");
        }
        // 执行修改操作
        userData.setId(user.getId());
        int row = userService.updateUser(userData);
        if (row > 0) {
            // 重置会话中的数据
            redisCache.set(uuid, gson.toJson(userData), Const.Time.ONE_DAY);
            session.setAttribute(Const.User.USERSTR, userData);
            return ServerResponse.createBySuccessMessage("修改信息成功");
        }
        return ServerResponse.createByErrorMessage("修改信息失败");
    }

    /**
     * 用户修改密码页面
     *
     * @return
     */
    @RequestMapping("/ajax/update/pwd")
    @ResponseBody
    public ServerResponse ajaxUpdatePwd(HttpSession session, User userData) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return ServerResponse.createByErrorCodeMessage(Const.User.NO_LOGIN, "用户未登录");
        }
        // 检查数据是否正确
        if (StringUtils.isBlank(userData.getPwd())) {
            return ServerResponse.createByErrorMessage("请输入密码");
        }
        // 执行修改操作
        userData.setId(user.getId());
        userData.setPwd(MD5Util.MD5EncodeUtf8(userData.getPwd()));
        int row = userService.updateUserPwd(userData);
        if (row > 0) {
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
        return ServerResponse.createBySuccessMessage("修改密码失败");
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/ajax/login")
    @ResponseBody
    public ServerResponse ajaxLogin(HttpServletResponse response, User user) {
        // 检查数据是否正确
        if (StringUtils.isBlank(user.getName())) {
            return ServerResponse.createByErrorMessage("请输入名称");
        }
        if (StringUtils.isBlank(user.getPwd())) {
            return ServerResponse.createByErrorMessage("请输入密码");
        }
        // 执行登录操作
        User getUserByName = userService.getUserByName(user.getName());
        if (ObjectUtils.isNull(getUserByName)) {
            // 注册账号
            user.setPwd(MD5Util.MD5EncodeUtf8(user.getPwd()));
            int row = userService.addUser(user);
            if (row <= 0) {
                return ServerResponse.createByErrorMessage("登录失败");
            }
            getUserByName = user;
        } else {
            if (!verifyPwd(user.getPwd(), getUserByName.getPwd())) {
                return ServerResponse.createByErrorMessage("密码错误");
            }
        }
        // 设置cookie
        String uuid = UUID.randomUUID().toString();
        setCookie(response, Const.User.UUID, uuid, Const.Time.ONE_DAY);
        redisCache.set(uuid, gson.toJson(getUserByName), Const.Time.ONE_DAY);
        return ServerResponse.createBySuccessMessage("登录成功");
    }

    /**
     * 用户退出页面
     *
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        // 移除Cookie
        session.removeAttribute(Const.User.USERSTR);
        // 移除缓存
        String uuid = WebUtils.getCookie(request, Const.User.UUID);
        redisCache.del(uuid);
        return "redirect:" + murongqingProperty.getDomainName();
    }

    /**
     * 验证密码是否正确
     *
     * @param originalPwd 原密码
     * @param importPwd   输入密码
     * @return
     */
    public boolean verifyPwd(String importPwd, String originalPwd) {
        return MD5Util.MD5EncodeUtf8(importPwd).equals(originalPwd);
    }

    @RequestMapping("/list/select")
    public String listSelect(HttpServletRequest request, HttpSession session, User userData, @RequestParam(required = false, defaultValue = "1") int pageNum, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return NO_LOGIN;
        }
        // 执行查询操作
        PageInfo<User> pageInfo = userService.getUserList(userData, pageNum, pageSize);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("user", user);
        return "/admin-user-list-select";
    }
}
