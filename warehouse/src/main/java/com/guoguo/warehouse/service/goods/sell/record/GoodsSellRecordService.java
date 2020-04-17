package com.guoguo.warehouse.service.goods.sell.record;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.common.DateConstant;
import com.guoguo.warehouse.common.constant.text.BrandTextConstant;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.dao.brand.BrandDao;
import com.guoguo.warehouse.dao.goods.sell.record.GoodsSellRecordDao;
import com.guoguo.warehouse.entity.brand.Brand;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.entity.goods.sell.record.GoodsSellRecord;
import com.guoguo.warehouse.service.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品售卖记录
 *
 * @author GC
 */
@Service
public class GoodsSellRecordService extends ServiceImpl<GoodsSellRecordDao, GoodsSellRecord> {

    @Autowired
    private RecordService recordService;

    /**
     * 添加商品售卖记录
     *
     * @param goods
     * @param userId
     * @param ipAddr
     * @return
     */
    public boolean addGoodsSellRecord(Goods goods, Long userId, String ipAddr) {
        GoodsSellRecord goodsSellRecord = new GoodsSellRecord();
        goodsSellRecord.setGoodsName(goods.getName());
        goodsSellRecord.setBrandName(goods.getBrandName());
        goodsSellRecord.setPrice(goods.getSellingPrice());
        goodsSellRecord.setAddTime(DateTools.dateToStr(new Date(), DateConstant.PATTERN));
        boolean b = save(goodsSellRecord);
        if (b) {
            recordService.insertRecord("添加售卖记录", goodsSellRecord, userId, ipAddr);
            return true;
        }
        return false;
    }
}
