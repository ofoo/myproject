package com.guoguo.warehouse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.dao.goods.GoodsDao;
import com.guoguo.warehouse.entity.goods.Goods;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseApplicationTests {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void select() {
        List<Goods> goodsList = goodsDao.selectList(null);
        Assert.assertEquals(3, goodsList.size());
        goodsList.forEach(System.out::println);
    }

    @Test
    public void insert() {
        Goods goods = new Goods();
        goods.setName("电饭煲");
        goods.setPrice("50");
        goods.setQuantity("5");
        goods.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        int insert = goodsDao.insert(goods);
        System.out.println("影响记录数：" + insert);
    }

    @Test
    public void selectById() {
        Goods goods = goodsDao.selectById(1);
        System.out.println(goods);
    }

    @Test
    public void selectByIds() {
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<Goods> goodsList = goodsDao.selectBatchIds(list);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "洗衣机");
        map.put("price", 500);
        List<Goods> goodsList = goodsDao.selectByMap(map);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "洗").lt("price", 600);
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper2() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "洗").between("price", 400, 600).isNotNull("quantity");
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper3() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "洗").or().ge("price", 50).orderByDesc("price").orderByAsc("id");
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper4() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(add_time,'%Y-%m-%d')={0}", "2020-04-04")
                .inSql("id", "select id from warehouse_goods where name like '洗%'");
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper5() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "洗").and(wq -> wq.lt("price", 600).or().isNotNull("quantity"));
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper6() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "洗").or(wq -> wq.lt("price", 600).gt("price", 400).isNotNull("quantity"));
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper7() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("price", 500).or().isNotNull("quantity")).likeRight("name", "洗");
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper8() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", Arrays.asList(1, 2, 3));
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper9() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", Arrays.asList(1, 2, 3)).last("limit 1");
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperSupper() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "洗").lt("price", 600);
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperSupper2() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "洗").lt("price", 600)
                .select(Goods.class, info -> !info.getColumn().equals("add_time") &&
                        !info.getColumn().equals("price"));
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void testCondition() {
        String name = "洗";
        String email = "";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(StringUtils.isNotBlank(email), "email", email);
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperEntity() {
        Goods whereGoods = new Goods();
        whereGoods.setName("洗衣机");
        whereGoods.setPrice("500");
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>(whereGoods);
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "洗衣机");
        params.put("price", null);
//        queryWrapper.allEq(params);
//        queryWrapper.allEq(params,false);
        queryWrapper.allEq((k, v) -> !k.equals("name"), params);
        List<Goods> goodsList = goodsDao.selectList(queryWrapper);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperMaps() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "洗").lt("price", 600);

        List<Map<String, Object>> list = goodsDao.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperMaps2() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(price) avg_price", "min(price) min_price", "max(price) max_price")
                .groupBy("id").having("sum(price)<{0}", 600);

        List<Map<String, Object>> list = goodsDao.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperObjs() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "洗").lt("price", 600);

        List<Object> list = goodsDao.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperCount() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "洗").lt("price", 600);

        Integer count = goodsDao.selectCount(queryWrapper);
        System.out.println(count);
    }

    /**
     * 返回两条数据会报错
     */
    @Test
    public void selectByWrapperOne() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "洗").lt("price", 600);

        Goods goods = goodsDao.selectOne(queryWrapper);
        System.out.println(goods);
    }

    @Test
    public void selectLambda() {
//        LambdaQueryWrapper<Goods> lambda = new QueryWrapper<Goods>().lambda();
//        LambdaQueryWrapper<Goods> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Goods> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(Goods::getName, "洗").lt(Goods::getPrice, "600");
        List<Goods> goodsList = goodsDao.selectList(lambdaQuery);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<Goods> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.likeRight(Goods::getName, "洗").and(lqw -> lqw.lt(Goods::getPrice, 600).or().isNotNull(Goods::getQuantity));
        List<Goods> goodsList = goodsDao.selectList(lambdaQuery);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectLambda3() {
        List<Goods> goodsList = new LambdaQueryChainWrapper<>(goodsDao)
                .like(Goods::getName, "洗").ge(Goods::getPrice, 500).list();
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectMy() {
        LambdaQueryWrapper<Goods> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.likeRight(Goods::getName, "洗").and(lqw -> lqw.lt(Goods::getPrice, 600).or().isNotNull(Goods::getQuantity));
        List<Goods> goodsList = goodsDao.selectAll(lambdaQuery);
        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectPage() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("price", 500);

        /*Page<Goods> page = new Page<>(1, 2);
        Page<Goods> iPage = goodsDao.selectPage(page, queryWrapper);
        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<Goods> goodsList = iPage.getRecords();*/

        Page<Map<String, Object>> page = new Page<>(1, 2, false);
        Page<Map<String, Object>> iPage = goodsDao.selectMapsPage(page, queryWrapper);
        System.out.println("总页数" + iPage.getPages());
        System.out.println("总记录数" + iPage.getTotal());
        List<Map<String, Object>> goodsList = iPage.getRecords();

        goodsList.forEach(System.out::println);
    }

    @Test
    public void selectMyPage() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("price", 500);

        Page<Goods> page = new Page<>(1, 2);
        IPage<Goods> iPage = goodsDao.selectGoodsPage(page, queryWrapper);
        System.out.println("总页数" + iPage.getPages());
        System.out.println("总记录数" + iPage.getTotal());
        List<Goods> goodsList = iPage.getRecords();

        goodsList.forEach(System.out::println);
    }

    @Test
    public void updateById() {
        Goods goods = new Goods();
        goods.setId(1L);
        goods.setPrice("600");
        goods.setQuantity("30");
        goods.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        int i = goodsDao.updateById(goods);
        System.out.println("影响记录数：" + i);
    }

    @Test
    public void updateByWrapper() {
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "电磁炉").eq("price", 50);
        Goods goods = new Goods();
        goods.setQuantity("10");
        int update = goodsDao.update(goods, updateWrapper);
        System.out.println("影响记录数：" + update);
    }

    @Test
    public void updateByWrapper2() {
        Goods whereGoods = new Goods();
        whereGoods.setName("电磁炉");

        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>(whereGoods);
        updateWrapper.eq("name", "电磁炉").eq("price", 50);
        Goods goods = new Goods();
        goods.setQuantity("10");
        int update = goodsDao.update(goods, updateWrapper);
        System.out.println("影响记录数：" + update);
    }

    @Test
    public void updateByWrapper3() {
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "电磁炉").eq("price", 50).set("Quantity", 20);

        int update = goodsDao.update(null, updateWrapper);
        System.out.println("影响记录数：" + update);
    }

    @Test
    public void updateByWrapperLambda() {
        LambdaUpdateWrapper<Goods> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(Goods::getName, "电磁炉").eq(Goods::getPrice, 50).set(Goods::getQuantity, 30);

        int update = goodsDao.update(null, lambdaUpdate);
        System.out.println("影响记录数：" + update);
    }

    @Test
    public void updateByWrapperLambdaChain() {
        boolean b = new LambdaUpdateChainWrapper<>(goodsDao)
                .eq(Goods::getName, "电磁炉").eq(Goods::getPrice, 50).set(Goods::getQuantity, 40).update();
        System.out.println(b);
    }

    @Test
    public void deleteById() {
        int i = goodsDao.deleteById(11L);
        System.out.println("删除条数：" + i);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "烤箱");
        columnMap.put("price", 50);
        int i = goodsDao.deleteByMap(columnMap);
        System.out.println("删除条数：" + i);
    }

    @Test
    public void deleteBatchIds() {
        int i = goodsDao.deleteBatchIds(Arrays.asList(7, 8, 9));
        System.out.println("删除条数：" + i);
    }

    @Test
    public void deleteByWrapper() {
        LambdaQueryWrapper<Goods> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(Goods::getPrice, 600).or().gt(Goods::getPrice, 700);
        int i = goodsDao.delete(lambdaQuery);
        System.out.println("删除条数：" + i);
    }
}
