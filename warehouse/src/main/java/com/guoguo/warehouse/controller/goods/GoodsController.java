package com.guoguo.warehouse.controller.goods;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.text.UpGoodsTextConstant;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.common.tool.StringTools;
import com.guoguo.warehouse.common.tool.WebTools;
import com.guoguo.warehouse.entity.brand.Brand;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.service.brand.BrandService;
import com.guoguo.warehouse.service.goods.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品管理
 *
 * @author GC
 */
@Controller
@RequestMapping("/warehouse/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "goods/";
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
    public String add(Model model) {
        List<Brand> list = brandService.list();
        model.addAttribute("brandList", list);
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
        // 查询商品信息
        Goods byId = goodsService.getById(id);
        model.addAttribute("goods", byId);
        // 查询品牌列表
        List<Brand> list = brandService.list();
        model.addAttribute("brandList", list);
        return getPagePath("save");
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
        lambdaQueryWrapper.like(StringUtils.isNotBlank(goods.getName()), Goods::getName, goods.getName())
                .like(StringUtils.isNotBlank(goods.getBrandName()),Goods::getBrandName,goods.getBrandName())
                .orderByDesc(Goods::getId);
        return goodsService.getGoodsListPage(lambdaQueryWrapper, page, limit);
    }

    /**
     * 保存商品
     *
     * @param goods
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(Goods goods, HttpSession session, HttpServletRequest request) {
        if (StringUtils.isBlank(goods.getName())) {
            return ResultJson.no("请输入商品名称");
        }
        if (StringUtils.isBlank(goods.getPrice())) {
            return ResultJson.no("请输入商品单价");
        }
        if (StringUtils.isBlank(goods.getQuantity())) {
            return ResultJson.no("请输入商品数量");
        }
        if (ObjectTools.isNull(goods.getId())) {
            goods.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }
        return goodsService.insertOrUpdate(goods, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 删除商品
     *
     * @param ids
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestParam String ids, HttpSession session, HttpServletRequest request) {
        return goodsService.deleteByIds(StringTools.strTurnListLong(ids), WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 修改上货数量
     *
     * @param goods
     * @return
     */
    @RequestMapping("/ajax/upGoodsQuantity/update")
    @ResponseBody
    public ResultJson ajaxUpGoodsQuantityUpdate(Goods goods, HttpSession session, HttpServletRequest request) {
        return goodsService.updateUpGoodsQuantityById(goods, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 完成商品上货
     *
     * @param list
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/ajax/quantity/upGoodsQuantity/update")
    @ResponseBody
    public ResultJson ajaxQuantityUpGoodsQuantityUpdate(@RequestBody List<Goods> list, HttpSession session, HttpServletRequest request) {
        if (ObjectTools.isNull(list)) {
            return ResultJson.yes(UpGoodsTextConstant.NAME + "成功");
        }
        List<Goods> goodsList = new ArrayList<>();
        for (Goods g : list) {
            Goods goods = new Goods();
            goods.setId(g.getId());
            goods.setQuantity(String.valueOf(Integer.parseInt(g.getQuantity()) + Integer.parseInt(g.getUpGoodsQuantity())));
            goods.setUpGoodsQuantity("");
            goodsList.add(goods);
        }
        return goodsService.updateQuantityAndUpGoodsQuantityByIdBatch(list, goodsList, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 取消商品
     *
     * @param list
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/ajax/upGoodsQuantity/update/batch")
    @ResponseBody
    public ResultJson ajaxUpGoodsQuantityUpdateBatch(@RequestBody List<Goods> list, HttpSession session, HttpServletRequest request) {
        if (ObjectTools.isNull(list)) {
            return ResultJson.yes("完成" + UpGoodsTextConstant.NAME);
        }
        List<Goods> goodsList = new ArrayList<>();
        for (Goods g : list) {
            Goods goods = new Goods();
            goods.setId(g.getId());
            goods.setUpGoodsQuantity("");
            goodsList.add(goods);
        }
        return goodsService.updateUpGoodsQuantityByIdBatch(list, goodsList, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 修改商品数量
     * @param id
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/ajax/quantity/update")
    @ResponseBody
    public ResultJson ajaxQuantityUpdate(@RequestParam Long id, HttpSession session, HttpServletRequest request) {
        return goodsService.updateQuantityById(id, WebTools.getUserId(session), WebTools.getIpAddr(request));
    }

    /**
     * 修改商品数量
     * @param id
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/quantity/update/{id}")
    public String quantityUpdate(@PathVariable Long id, HttpSession session, HttpServletRequest request) {
        goodsService.updateQuantityById(id, WebTools.getUserId(session), WebTools.getIpAddr(request));
        return "common/success";
    }

    /**
     * 获取商品二维码
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/qrCode")
    public String qrCode(@RequestParam Long id,Model model){
        model.addAttribute("id",id);
        return getPagePath("qr-code");
    }
}
