package com.guoguo.warehouse.controller.console;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.entity.maintain.Maintain;
import com.guoguo.warehouse.service.brand.BrandService;
import com.guoguo.warehouse.service.goods.GoodsService;
import com.guoguo.warehouse.service.goods.sell.record.GoodsSellRecordService;
import com.guoguo.warehouse.service.maintain.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制台管理
 *
 * @author GC
 */
@Controller
@RequestMapping("/warehouse")
public class ConsoleController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private MaintainService maintainService;
    @Autowired
    private GoodsSellRecordService goodsSellRecordService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "console/";
        return pagePackage + pageName;
    }

    /**
     * 控制台
     *
     * @return
     */
    @RequestMapping("/console")
    public String console() {
        return getPagePath("console");
    }

    @RequestMapping("/console/index")
    public String index(Model model) {
        // 商品数量
        int goodsCount = goodsService.count();
        model.addAttribute("goodsCount", goodsCount);
        // 上货数量
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(Goods::getUpGoodsQuantity,"");
        int upGoodsCount = goodsService.count(lambdaQueryWrapper);
        model.addAttribute("upGoodsCount", upGoodsCount);
        // 维修数量
        int maintainCount = maintainService.count();
        model.addAttribute("maintainCount", maintainCount);
        // 维修完成数量
        LambdaQueryWrapper<Maintain> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Maintain::getStatus,1);
        int finishCount = maintainService.count(queryWrapper);
        model.addAttribute("finishCount", finishCount);
        // 商品售卖数量
        int goodsSellCount = goodsSellRecordService.count();
        model.addAttribute("goodsSellCount", goodsSellCount);
        return getPagePath("console");
    }

    @RequestMapping("/demo")
    public String demo() {
        return getPagePath("demo");
    }
}
