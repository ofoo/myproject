package com.guoguo.ynz.controller.role;

import com.guoguo.ynz.entity.role.Role;
import com.guoguo.ynz.service.role.RoleService;
import com.guoguo.ynz.tool.constant.RoleConstant;
import com.guoguo.ynz.tool.result.DataJson;
import com.guoguo.ynz.tool.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色
 */
@Controller
@RequestMapping("/ynz/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "role/";
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
        request.setAttribute("listPageTitle", RoleConstant.ADD_PAGE_TITLE);
        request.setAttribute("addPageTitle", RoleConstant.ADD_PAGE_TITLE);
        request.setAttribute("updatePageTitle", RoleConstant.ADD_PAGE_TITLE);
        return getPagePath("list");
    }

    /**
     * 分页查询角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(Role role) {
        return roleService.getRoleList(role);
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
        Role role = roleService.getRoleById(id);
        request.setAttribute("role", role);
        return getPagePath("save");
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    /**
     * 删除角色
     *
     * @param roleList
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestBody List<Role> roleList) {
        return roleService.deleteRoleByIds(roleList);
    }
}
