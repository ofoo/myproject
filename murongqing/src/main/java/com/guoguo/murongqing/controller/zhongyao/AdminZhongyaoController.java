package com.guoguo.murongqing.controller.zhongyao;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.Const;
import com.guoguo.common.ServerResponse;
import com.guoguo.murongqing.controller.common.BaseController;
import com.guoguo.murongqing.dao.prescription.PrescriptionZhongyaoDao;
import com.guoguo.murongqing.entity.prescription.PrescriptionZhongyao;
import com.guoguo.murongqing.entity.zhongyao.Category;
import com.guoguo.murongqing.entity.zhongyao.Zhongyao;
import com.guoguo.murongqing.entity.common.Fenye;
import com.guoguo.murongqing.entity.user.User;
import com.guoguo.murongqing.service.prescription.PrescriptionZhongyaoService;
import com.guoguo.murongqing.service.zhongyao.CategoryService;
import com.guoguo.murongqing.service.zhongyao.ZhongyaoService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中药管理
 */
@Controller
@RequestMapping("/admin/zhongyao")
@Slf4j
public class AdminZhongyaoController extends BaseController {

    @Autowired
    private ZhongyaoService zhongyaoService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PrescriptionZhongyaoService prescriptionZhongyaoService;

    /**
     * 中药列表
     *
     * @param request
     * @param session
     * @param zhongyao
     * @param fenye
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpSession session, Zhongyao zhongyao, Fenye fenye) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return NO_LOGIN;
        }
        PageInfo<Zhongyao> pageInfo = zhongyaoService.getZhongyaoList(zhongyao, fenye);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("zhongyao", zhongyao);
        return "admin-zhongyao-list";
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
            Zhongyao zhongyao = zhongyaoService.getZhongyaoById(id);
            model.addAttribute("zhongyao", zhongyao);
        }
        // 中药类别信息
        List<Category> categoryList = categoryService.getCategoryAll();
        model.addAttribute("categoryList",categoryList);
        return "admin-zhongyao-save";
    }

    /**
     * 保存中药
     *
     * @param session
     * @param zhongyao
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(HttpSession session, Zhongyao zhongyao) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return ServerResponse.createByErrorCodeMessage(Const.User.NO_ACCESS, "用户没有登录");
        }
        // 验证传入数据是否正确
        if (StringUtils.isBlank(zhongyao.getName())) {
            return ServerResponse.createByErrorMessage("请输入中药名称");
        }
        // 执行操作
        if (ObjectUtils.isNull(zhongyao.getId())) {
            int row = zhongyaoService.addZhongyao(zhongyao);
            if (row > 0) {
                return ServerResponse.createBySuccessMessage("添加成功");
            }
            return ServerResponse.createByErrorMessage("添加失败");
        } else {
            int row = zhongyaoService.updateZhongyao(zhongyao);
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
     * @param zhongyao
     * @return
     */
    @RequestMapping("/ajax/del")
    @ResponseBody
    public ServerResponse ajaxDel(HttpSession session, Zhongyao zhongyao) {
        // 读取登录用户
        User user = (User) session.getAttribute(Const.User.USERSTR);
        if (ObjectUtils.isNull(user)) {
            return ServerResponse.createByErrorCodeMessage(Const.User.NO_ACCESS, "用户没有登录");
        }
        // 验证传入数据是否正确
        if (ObjectUtils.isNull(zhongyao.getIds())) {
            return ServerResponse.createByErrorMessage("请选择中药");
        }
        // 执行删除操作
        zhongyao.setDelete(1);
        int row = zhongyaoService.updateZhongyaoDeleteByIds(zhongyao);
        if (row > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 展示数据
     *
     * @return
     */
    @RequestMapping("/show")
    public String show(Model model) {
        List<Zhongyao> zhongyaoList = zhongyaoService.getZhongyaoAll();
        if (ObjectUtils.isNotNull(zhongyaoList)) {
            StringBuffer data=new StringBuffer();
            for (Zhongyao zhongyao : zhongyaoList) {
                Map<String, String> map = new HashMap<>();
                map.put("name", zhongyao.getName());
                map.put("x", zhongyao.getX());
                map.put("y", zhongyao.getY());
                map.put("price", zhongyao.getPrice());
                map.put("image", zhongyao.getImage());
                data.append("\"" + zhongyao.getName() + "\":" + gson.toJson(map));
//                data.append(gson.toJson(map));
                data.append(",");
            }
            model.addAttribute("data", data);
        }
        return "admin-zhongyao-show";
    }

    /**
     * 中药选择
     * @param model
     * @return
     */
    @RequestMapping("/select")
    public String select(Model model) {
        //分类列表
        List<Category> categoryList = categoryService.getCategoryAll();
        model.addAttribute("categoryList", categoryList);
        return "admin-zhongyao-select";
    }
}
