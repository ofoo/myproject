package com.guoguo.warehouse.service.goods;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.text.GoodsTextConstant;
import com.guoguo.warehouse.common.constant.text.UpGoodsTextConstant;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.common.tool.StringTools;
import com.guoguo.warehouse.dao.goods.GoodsDao;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.service.goods.sell.record.GoodsSellRecordService;
import com.guoguo.warehouse.service.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author GC
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {
    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodsSellRecordService goodsSellRecordService;

    @Override
    public DataJson getGoodsListPage(LambdaQueryWrapper<Goods> lambdaQueryWrapper, Integer page, Integer limit) {
        Page<Goods> p = new Page<>(page, limit);
        Page<Goods> goodsPage = page(p, lambdaQueryWrapper);
        return DataJson.list(goodsPage.getTotal(), goodsPage.getRecords());
    }

    @Override
    @Transactional
    public ResultJson insertOrUpdate(Goods goods, Long userId, String ip) {
        boolean b;
        String msg;
        if (ObjectTools.isNull(goods.getId())) {
            goods.setUserId(userId);
            b = save(goods);
            if (b) {
                msg = "添加" + GoodsTextConstant.NAME + "成功";
                recordService.insertRecord(msg, goods, userId, ip);
            } else {
                msg = "添加失败";
            }
        } else {
            Goods before = getById(goods.getId());
            b = updateById(goods);
            if (b) {
                msg = "修改" + GoodsTextConstant.NAME + "成功";
                recordService.updateRecord(msg, before, goods, userId, ip);
            } else {
                msg = "修改失败";
            }
        }
        return ResultJson.result(b, msg);
    }

    @Override
    public ResultJson deleteByIds(List<Long> ids, Long userId, String ip) {
        List<Goods> goodsList = listByIds(ids);
        boolean b = removeByIds(ids);
        if (b) {
            String msg = ids.size() + "件" + GoodsTextConstant.NAME + "删除成功";
            List<Object> list = new ArrayList<>(goodsList);
            recordService.deleteRecord(msg, list, userId, ip);
            return ResultJson.yes(msg);
        }
        return ResultJson.no(GoodsTextConstant.NAME + "删除失败");
    }

    @Override
    @Transactional
    public ResultJson updateUpGoodsQuantityById(Goods goods, Long userId, String ip) {
        Goods before = getById(goods.getId());
        if (before.getUpGoodsQuantity().equals(goods.getUpGoodsQuantity())) {
            return ResultJson.yes(UpGoodsTextConstant.NAME + "数量编辑成功");
        }
        boolean b = updateById(goods);
        if (b) {
            String msg = UpGoodsTextConstant.NAME + "数量编辑成功";
            recordService.updateRecord(msg, before, goods, userId, ip);
            return ResultJson.yes(msg);
        } else {
            return ResultJson.no(UpGoodsTextConstant.NAME + "数量编辑失败");
        }
    }

    @Override
    @Transactional
    public ResultJson updateQuantityAndUpGoodsQuantityByIdBatch(List<Goods> originalList, List<Goods> list, Long userId, String ip) {
        boolean b = updateBatchById(list);
        if (b) {
            String msg = originalList.size() + "件" + UpGoodsTextConstant.NAME + "成功";
            recordService.updateRecord(msg, originalList, list, userId, ip);
            return ResultJson.yes(msg);
        }
        return ResultJson.no(UpGoodsTextConstant.NAME + "失败");
    }

    @Override
    @Transactional
    public ResultJson updateUpGoodsQuantityByIdBatch(List<Goods> originalList, List<Goods> list, Long userId, String ip) {
        boolean b = updateBatchById(list);
        if (b) {
            String msg = originalList.size() + "件" + UpGoodsTextConstant.NAME + "取消成功";
            recordService.updateRecord(msg, originalList, list, userId, ip);
            return ResultJson.yes(msg);
        } else {
            return ResultJson.no(UpGoodsTextConstant.NAME + "取消失败");
        }
    }

    @Override
    @Transactional
    public ResultJson updateQuantityById(Long id, Long userId, String ipAddr) {
        Goods goods = getById(id);
        Goods updateGoods = new Goods();
        updateGoods.setId(goods.getId());
        updateGoods.setQuantity(StringTools.str1SubtractStr2(goods.getQuantity(), "1"));
        if (ObjectTools.isNull(updateGoods.getQuantity())) {
            return ResultJson.yes("操作成功");
        }
        boolean b = updateById(updateGoods);
        if (b) {
            // 添加售卖记录
            goodsSellRecordService.addGoodsSellRecord(goods, userId, ipAddr);
            // 添加操作记录
            recordService.updateRecord("商品数量减1", goods, updateGoods, userId, ipAddr);
            return ResultJson.yes("操作成功");
        }
        return ResultJson.no("操作失败");
    }
}
