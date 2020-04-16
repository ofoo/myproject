package com.guoguo.warehouse.controller.brand;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.text.BrandTextConstant;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.common.tool.WebTools;
import com.guoguo.warehouse.entity.brand.Brand;
import com.guoguo.warehouse.service.brand.BrandService;
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
 * 品牌管理
 *
 * @author GC
 */
@Controller
@RequestMapping("/warehouse/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "brand/";
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
        Brand brand = brandService.getById(id);
        model.addAttribute("brand", brand);
        return getPagePath("save");
    }

    /**
     * 数据列表
     *
     * @param brand
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(Brand brand, Integer page, Integer limit) {
        LambdaQueryWrapper<Brand> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(brand.getName()), Brand::getName, brand.getName()).orderByDesc(Brand::getId);
        return brandService.getListPage(lambdaQueryWrapper, page, limit);
    }

    /**
     * 保存商品
     *
     * @param brand
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(Brand brand, HttpSession session, HttpServletRequest request) {
        if (StringUtils.isBlank(brand.getName())) {
            return ResultJson.no("请输入" + BrandTextConstant.NAME + "名称");
        }
        if (ObjectTools.isNull(brand.getId())) {
            brand.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }
        return brandService.insertOrUpdate(brand, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 删除商品
     *
     * @param brandList
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestBody List<Brand> brandList, HttpSession session, HttpServletRequest request) {
        return brandService.deleteByIds(brandList, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }
}
