package com.guoguo.warehouse.controller.maintain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.text.BrandTextConstant;
import com.guoguo.warehouse.common.constant.text.MaintainTextConstant;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.common.tool.WebTools;
import com.guoguo.warehouse.entity.maintain.Maintain;
import com.guoguo.warehouse.service.maintain.MaintainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 维修管理
 *
 * @author GC
 */
@Controller
@RequestMapping("/warehouse/maintain")
public class MaintainController {

    @Autowired
    private MaintainService maintainService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "maintain/";
        return pagePackage + pageName;
    }

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return getPagePath("list");
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
    public String update(@PathVariable Long id, Model model) {
        Maintain maintain = maintainService.getById(id);
        model.addAttribute("maintain", maintain);
        return getPagePath("save");
    }

    /**
     * 数据列表
     *
     * @param maintain
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(Maintain maintain, Integer page, Integer limit) {
        LambdaQueryWrapper<Maintain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(maintain.getGoodsName()), Maintain::getGoodsName, maintain.getGoodsName())
                .like(StringUtils.isNotBlank(maintain.getBrandName()), Maintain::getBrandName, maintain.getBrandName())
                .like(StringUtils.isNotBlank(maintain.getClientName()), Maintain::getClientName, maintain.getClientName())
                .like(StringUtils.isNotBlank(maintain.getClientPhone()), Maintain::getClientPhone, maintain.getClientPhone())
                .orderByDesc(Maintain::getId);
        return maintainService.getListPage(lambdaQueryWrapper, page, limit);
    }

    /**
     * 保存商品
     *
     * @param maintain
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(Maintain maintain, HttpSession session, HttpServletRequest request) {
        if (StringUtils.isBlank(maintain.getGoodsName())) {
            return ResultJson.no("请输入" + MaintainTextConstant.NAME + "名称");
        }
        /*if (StringUtils.isBlank(maintain.getGoodsName())) {
            return ResultJson.no("请输入" + BrandTextConstant.NAME + "名称");
        }*/
        if (StringUtils.isBlank(maintain.getSellingPrice())) {
            return ResultJson.no("请输入维修价格");
        }
        if (StringUtils.isBlank(maintain.getClientName())) {
            return ResultJson.no("请输入顾客姓名");
        }
        if (StringUtils.isBlank(maintain.getClientPhone())) {
            return ResultJson.no("请输入顾客电话");
        }
        if (StringUtils.isBlank(maintain.getClientAddress())) {
            return ResultJson.no("请输入顾客住址");
        }
        if (ObjectTools.isNull(maintain.getId())) {
            maintain.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }
        return maintainService.insertOrUpdate(maintain, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 删除商品
     *
     * @param maintainList
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestBody List<Maintain> maintainList, HttpSession session, HttpServletRequest request) {
        return maintainService.deleteByIds(maintainList, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 修改维修状态
     *
     * @param id
     * @param status
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/ajax/update/status")
    @ResponseBody
    public ResultJson ajaxUpdateStatus(@RequestParam Long id, @RequestParam String status, HttpSession session, HttpServletRequest request) {
        return maintainService.updateStatusById(id, status, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }
}
