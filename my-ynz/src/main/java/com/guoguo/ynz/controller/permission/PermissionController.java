package com.guoguo.ynz.controller.permission;

import com.guoguo.ynz.entity.permission.Permission;
import com.guoguo.ynz.service.permission.PermissionService;
import com.guoguo.ynz.tool.constant.PermissionConstant;
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
 * 权限
 */
@Controller
@RequestMapping("/ynz/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "permission/";
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
        request.setAttribute("listPageTitle", PermissionConstant.ADD_PAGE_TITLE);
        request.setAttribute("addPageTitle", PermissionConstant.ADD_PAGE_TITLE);
        request.setAttribute("updatePageTitle", PermissionConstant.ADD_PAGE_TITLE);
        return getPagePath("list");
    }

    /**
     * 分页查询权限
     *
     * @param permission
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(Permission permission) {
        return permissionService.getPermissionList(permission);
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
        Permission permission = permissionService.getPermissionById(id);
        request.setAttribute("permission", permission);
        return getPagePath("save");
    }

    /**
     * 保存权限
     *
     * @param permission
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }

    /**
     * 删除权限
     *
     * @param permissionList
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestBody List<Permission> permissionList) {
        return permissionService.deletePermissionByIds(permissionList);
    }
}
