package com.guoguo.warehouse.service.record;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.constant.common.ObjectConstant;
import com.guoguo.warehouse.common.constant.function.RecordConstant;
import com.guoguo.warehouse.common.constant.text.GoodsTextConstant;
import com.guoguo.warehouse.common.tool.DateTools;
import com.guoguo.warehouse.dao.record.RecordDao;
import com.guoguo.warehouse.entity.record.Record;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GC
 */
@Service
public class RecordService extends ServiceImpl<RecordDao, Record> {

    public DataJson getListPage(LambdaQueryWrapper<Record> lambdaQueryWrapper, Integer page, Integer limit) {
        Page<Record> p = new Page<>(page, limit);
        Page<Record> goodsPage = page(p, lambdaQueryWrapper);
        return DataJson.list(goodsPage.getTotal(), goodsPage.getRecords());
    }

    /**
     * 添加记录
     *
     * @param explanin
     * @param obj
     * @param userId
     */
    public void insertRecord(String explanin, Object obj, Long userId, String ip) {
        Record record = new Record();
        record.setExplain(explanin);
        record.setData(ObjectConstant.gson.toJson(obj));
        record.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        record.setUserId(userId);
        record.setIp(ip);
        record.setType(RecordConstant.INSERT);
        boolean save = save(record);
        log.debug(GoodsTextConstant.NAME + "添加记录：" + save);
    }

    /**
     * 修改记录
     *
     * @param explain
     * @param before
     * @param later
     * @param userId
     */
    public void updateRecord(String explain, Object before, Object later, Long userId, String ip) {
        Map<String, Object> goodsMap = new HashMap<>(2);
        goodsMap.put("before", before);
        goodsMap.put("later", later);
        Record record = new Record();
        record.setExplain(explain);
        record.setData(ObjectConstant.gson.toJson(goodsMap));
        record.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        record.setUserId(userId);
        record.setIp(ip);
        record.setType(RecordConstant.UPDATE);
        boolean save = save(record);
        log.debug(GoodsTextConstant.NAME + "修改记录：" + save);
    }

    /**
     * 删除记录
     * @param explanin
     * @param list
     * @param userId
     * @param ip
     */
    public void deleteRecord(String explanin, List<Object> list, Long userId, String ip) {
        Record record = new Record();
        record.setExplain(explanin);
        if (list.size()>1){
            record.setData(ObjectConstant.gson.toJson(list));
        }else{
            record.setData(ObjectConstant.gson.toJson(list.get(0)));
        }
        record.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        record.setUserId(userId);
        record.setIp(ip);
        record.setType(RecordConstant.DELETE);
        boolean save = save(record);
        log.debug("删除记录：" + save);
    }

    /**
     * 查询记录
     *
     * @return
     */
    public DataJson selectRecord() {
        return null;
    }

    /**
     * 用户登录记录
     *
     * @param explanin
     * @param obj
     * @param userId
     * @param ip
     */
    public void loginRecord(String explanin, Object obj, Long userId, String ip) {
        Record record = new Record();
        record.setExplain(explanin);
        record.setData(ObjectConstant.gson.toJson(obj));
        record.setAddTime(DateTools.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        record.setUserId(userId);
        record.setIp(ip);
        record.setType(RecordConstant.LOGIN);
        boolean save = save(record);
        log.debug(GoodsTextConstant.NAME + "添加记录：" + save);
    }
}
