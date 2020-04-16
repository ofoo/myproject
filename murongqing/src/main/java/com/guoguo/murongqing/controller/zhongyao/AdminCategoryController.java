package com.guoguo.murongqing.controller.zhongyao;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.Const;
import com.guoguo.common.ServerResponse;
import com.guoguo.murongqing.controller.common.BaseController;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.user.User;
import com.guoguo.murongqing.entity.zhongyao.Category;
import com.guoguo.murongqing.service.zhongyao.CategoryService;
import com.guoguo.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中药分类
 */
@Controller
@RequestMapping("/admin/category")
@Slf4j
public class AdminCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 中药列表
     *
     * @param request
     * @param session
     * @param category
     * @param fenye
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpSession session, Category category, Fenye fenye) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return NO_LOGIN;
        }
        PageInfo<Category> pageInfo = categoryService.getCategoryList(category, fenye);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("category", category);
        return "admin-category-list";
    }

    /**
     * 保存中药页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/save")
    public String save(Model model, HttpSession session, Long id) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return NO_ACCESS;
        }
        if (ObjectUtils.isNotNull(id)) {
            Category category = categoryService.getCategoryById(id);
            model.addAttribute("category", category);
        }
        return "admin-category-save";
    }

    /**
     * 保存中药
     *
     * @param session
     * @param category
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(HttpSession session, Category category) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return ServerResponse.createByErrorCodeMessage(Const.User.NO_ACCESS, "用户没有登录");
        }
        // 验证传入数据是否正确
        if (StringUtils.isBlank(category.getName())) {
            return ServerResponse.createByErrorMessage("请输入中药名称");
        }
        // 执行操作
        if (ObjectUtils.isNull(category.getId())) {
            int row = categoryService.addCategory(category);
            if (row > 0) {
                return ServerResponse.createBySuccessMessage("添加成功");
            }
            return ServerResponse.createByErrorMessage("添加失败");
        } else {
            int row = categoryService.updateCategory(category);
            if (row > 0) {
                return ServerResponse.createBySuccessMessage("修改成功");
            }
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    /**
     * 删除中药
     *
     * @param session
     * @param category
     * @return
     */
    @RequestMapping("/ajax/del")
    @ResponseBody
    public ServerResponse ajaxDel(HttpSession session, Category category) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return ServerResponse.createByErrorCodeMessage(Const.User.NO_ACCESS, "用户没有登录");
        }
        // 验证传入数据是否正确
        if (ObjectUtils.isNull(category.getIds())) {
            return ServerResponse.createByErrorMessage("请选择中药");
        }
        // 执行删除操作
        category.setDelete(1);
        int row = categoryService.updateCategoryDeleteByIds(category);
        if (row > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 中药选择
     *
     * @return
     */
    @RequestMapping("/select")
    public String select(Model model) {
        List<Category> categoryList = categoryService.getCategoryAll();
        model.addAttribute("categoryList", categoryList);
        return "admin-category-select";
    }
}
