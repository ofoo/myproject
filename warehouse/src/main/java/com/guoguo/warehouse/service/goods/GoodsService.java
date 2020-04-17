package com.guoguo.warehouse.service.goods;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.entity.goods.Goods;

import java.util.List;

/**
 * 商品
 *
 * @author GC
 */
public interface GoodsService extends IService<Goods> {

    DataJson getGoodsListPage(LambdaQueryWrapper<Goods> lambdaQueryWrapper, Integer page, Integer limit);

    ResultJson insertOrUpdate(Goods goods, Long userId, String ip);

    ResultJson deleteByIds(List<Long> ids,Long userId,String ip);

    ResultJson updateUpGoodsQuantityById(Goods goods, Long userId, String ip);

    ResultJson updateQuantityAndUpGoodsQuantityByIdBatch(List<Goods> originalList, List<Goods> list, Long userId, String ip);

    public ResultJson updateUpGoodsQuantityByIdBatch(List<Goods> originalList, List<Goods> list, Long userId, String ip);

    ResultJson updateQuantityById(Long id, Long userId, String ipAddr);
}
