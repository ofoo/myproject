package com.guoguo.warehouse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.service.goods.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void getOne() {
        Goods one = goodsService.getOne(Wrappers.<Goods>lambdaQuery().gt(Goods::getPrice, 50), false);
        System.out.println(one);
    }

    @Test
    public void bath() {
        Goods goods1 = new Goods();
        goods1.setName("洗衣机");
        goods1.setPrice("400");
        goods1.setQuantity("5");
        goods1.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));

        Goods goods2 = new Goods();
        goods2.setName("电饭锅");
        goods2.setPrice("400");
        goods2.setQuantity("5");
        goods2.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));

        List<Goods> goodsList = Arrays.asList(goods1, goods2);
        boolean saveBatch = goodsService.saveBatch(goodsList);
        System.out.println(saveBatch);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void chain() {
        List<Goods> goodsList = goodsService.lambdaQuery().gt(Goods::getPrice, 50).like(Goods::getName, "洗").list();
        goodsList.forEach(System.out::println);
    }

    @Test
    public void chain2() {
        boolean update = goodsService.lambdaUpdate().eq(Goods::getPrice, 400).set(Goods::getPrice, 300).update();
        System.out.println(update);
    }

    @Test
    public void chain3() {
        boolean remove = goodsService.lambdaUpdate().eq(Goods::getPrice, 300).remove();
        System.out.println(remove);
    }

    @Test
    public void selectPage() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("price", 500);

        Page<Goods> page = new Page<>(1, 2);
        Page<Goods> iPage = goodsService.page(page, queryWrapper);
        System.out.println("总页数" + iPage.getPages());
        System.out.println("总记录数" + iPage.getTotal());
        List<Goods> goodsList = iPage.getRecords();

        goodsList.forEach(System.out::println);
    }
}
