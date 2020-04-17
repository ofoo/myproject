package com.guoguo.warehouse;

import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.entity.goods.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    @Test
    public void insert() {
        Goods goods = new Goods();
        goods.setName("微波炉");
        goods.setPrice("50");
        goods.setQuantity("5");
        goods.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        boolean insert = goods.insert();
        System.out.println(insert);
    }

    @Test
    public void selectById() {
        Goods goods = new Goods();
        Goods selectById = goods.selectById(12);
        System.out.println(goods==selectById);
        System.out.println(selectById);
    }

    @Test
    public void selectById2() {
        Goods goods = new Goods();
        goods.setId(12L);
        Goods selectById = goods.selectById();
        System.out.println(goods==selectById);
        System.out.println(selectById);
    }

    @Test
    public void updateById(){
        Goods goods = new Goods();
        goods.setId(12L);
        goods.setName("吹风机");
        boolean b = goods.updateById();
        System.out.println(b);
    }

    @Test
    public void deleteById(){
        Goods goods = new Goods();
        goods.setId(14L);
        boolean b = goods.deleteById();
        System.out.println(b);
    }

    @Test
    public void insertOrUpdate(){
        Goods goods = new Goods();
        goods.setId(15L);
        goods.setQuantity("10");
        boolean b = goods.insertOrUpdate();
        System.out.println(b);
    }
}
