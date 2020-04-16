package com.guoguo.warehouse.controller.upgoods;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.common.tool.StringTools;
import com.guoguo.warehouse.common.tool.WebTools;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.service.goods.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 上货管理
 *
 * @author GC
 */
@Controller
@RequestMapping("/warehouse/upgoods")
public class UpGoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "upgoods/";
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
     * 数据列表
     *
     * @param goods
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(Goods goods, Integer page, Integer limit) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(goods.getName()), Goods::getName, goods.getName()).ne(Goods::getUpGoodsQuantity, "").orderByDesc(Goods::getId);
        return goodsService.getGoodsListPage(lambdaQueryWrapper, page, limit);
    }
}
