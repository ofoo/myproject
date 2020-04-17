package com.guoguo.warehouse.service.maintain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.text.MaintainTextConstant;
import com.guoguo.warehouse.common.constant.text.MaintainTextConstant;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.dao.maintain.MaintainDao;
import com.guoguo.warehouse.entity.brand.Brand;
import com.guoguo.warehouse.entity.maintain.Maintain;
import com.guoguo.warehouse.service.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌
 *
 * @author GC
 */
@Service
public class MaintainService extends ServiceImpl<MaintainDao, Maintain> {
    @Autowired
    private RecordService recordService;

    /**
     * 品牌数据列表
     *
     * @param lambdaQueryWrapper
     * @param page
     * @param limit
     * @return
     */
    public DataJson getListPage(LambdaQueryWrapper<Maintain> lambdaQueryWrapper, Integer page, Integer limit) {
        Page<Maintain> p = new Page<>(page, limit);
        Page<Maintain> dataPage = page(p, lambdaQueryWrapper);
        return DataJson.list(dataPage.getTotal(), dataPage.getRecords());
    }

    /**
     * 添加和修改品牌
     *
     * @param maintain
     * @param userId
     * @param ip
     * @return
     */
    @Transactional
    public ResultJson insertOrUpdate(Maintain maintain, Long userId, String ip) {
        boolean b;
        String msg;
        if (ObjectTools.isNull(maintain.getId())) {
            maintain.setUserId(userId);
            b = save(maintain);
            if (b) {
                msg = MaintainTextConstant.NAME + "添加成功";
                recordService.insertRecord(msg, maintain, userId, ip);
            } else {
                msg = MaintainTextConstant.NAME + "添加失败";
            }
        } else {
            Maintain before = getById(maintain.getId());
            b = updateById(maintain);
            if (b) {
                msg = MaintainTextConstant.NAME + "修改成功";
                recordService.updateRecord(msg, before, maintain, userId, ip);
            } else {
                msg = MaintainTextConstant.NAME + "修改失败";
            }
        }
        return ResultJson.result(b, msg);
    }

    /**
     * 根据id串删除品牌
     *
     * @param maintainList
     * @param userId
     * @param ip
     * @return
     */
    public ResultJson deleteByIds(List<Maintain> maintainList, Long userId, String ip) {
        List<Long> idList = new ArrayList<>();
        for (Maintain maintain : maintainList) {
            idList.add(maintain.getId());
        }
        boolean b = removeByIds(idList);
        if (b) {
            String msg = idList.size() + "个" + MaintainTextConstant.NAME + "删除成功";
            List<Object> list = new ArrayList<>(maintainList);
            recordService.deleteRecord(msg, list, userId, ip);
            return ResultJson.yes(msg);
        }
        return ResultJson.no(MaintainTextConstant.NAME + "删除失败");
    }

    @Transactional
    public ResultJson updateStatusById(Long id, String status, Long userId, String ip) {
        Maintain before = getById(id);
        Maintain maintain = new Maintain();
        maintain.setId(id);
        maintain.setStatus("true".equalsIgnoreCase(status) ? 1 : 0);
        boolean b = updateById(maintain);
        if (b) {
            String msg = "true".equalsIgnoreCase(status) ? MaintainTextConstant.NAME + "成功" : "取消完成状态成功";
            recordService.updateRecord(msg, before, maintain, userId, ip);
            return ResultJson.yes(msg);
        }
        return ResultJson.no("操作失败");
    }
}
