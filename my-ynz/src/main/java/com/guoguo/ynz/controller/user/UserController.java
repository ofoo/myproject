package com.guoguo.ynz.controller.user;

import com.guoguo.ynz.entity.user.User;
import com.guoguo.ynz.service.user.UserService;
import com.guoguo.ynz.tool.constant.UserConstant;
import com.guoguo.ynz.tool.result.DataJson;
import com.guoguo.ynz.tool.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录
 */
@Controller
@RequestMapping("/ynz/user")
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
     * 列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.setAttribute("listPageTitle", UserConstant.ADD_PAGE_TITLE);
        request.setAttribute("addPageTitle", UserConstant.ADD_PAGE_TITLE);
        request.setAttribute("updatePageTitle", UserConstant.ADD_PAGE_TITLE);
        return getPagePath("list");
    }

    /**
     * 分页查询用户数据
     * @param user
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(User user) {
        return userService.getUserList(user);
    }

    /**
     * 添加页面
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return getPagePath("save");
    }

    /**
     * 修改页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request) {
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        return getPagePath("save");
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * 删除
     *
     * @param userList
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestBody List<User> userList) {
        return userService.deleteUserByIds(userList);
    }
}
