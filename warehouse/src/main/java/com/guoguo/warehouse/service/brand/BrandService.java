package com.guoguo.warehouse.service.brand;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.common.ResultJson;
import com.guoguo.warehouse.common.constant.text.BrandTextConstant;
import com.guoguo.warehouse.common.tool.ObjectTools;
import com.guoguo.warehouse.dao.brand.BrandDao;
import com.guoguo.warehouse.entity.brand.Brand;
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
public class BrandService extends ServiceImpl<BrandDao, Brand> {
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
    public DataJson getListPage(LambdaQueryWrapper<Brand> lambdaQueryWrapper, Integer page, Integer limit) {
        Page<Brand> p = new Page<>(page, limit);
        Page<Brand> dataPage = page(p, lambdaQueryWrapper);
        return DataJson.list(dataPage.getTotal(), dataPage.getRecords());
    }

    /**
     * 添加和修改品牌
     *
     * @param brand
     * @param userId
     * @param ip
     * @return
     */
    @Transactional
    public ResultJson insertOrUpdate(Brand brand, Long userId, String ip) {
        boolean b;
        String msg;
        if (ObjectTools.isNull(brand.getId())) {
            brand.setUserId(userId);
            b = save(brand);
            if (b) {
                msg = BrandTextConstant.NAME + "添加成功";
                recordService.insertRecord(msg, brand, userId, ip);
            } else {
                msg = BrandTextConstant.NAME + "添加失败";
            }
        } else {
            Brand before = getById(brand.getId());
            b = updateById(brand);
            if (b) {
                msg = BrandTextConstant.NAME + "修改成功";
                recordService.updateRecord(msg, before, brand, userId, ip);
            } else {
                msg = BrandTextConstant.NAME + "修改失败";
            }
        }
        return ResultJson.result(b, msg);
    }

    /**
     * 根据id串删除品牌
     *
     * @param brandList
     * @param userId
     * @param ip
     * @return
     */
    public ResultJson deleteByIds(List<Brand> brandList, Long userId, String ip) {
        List<Long> idList = new ArrayList<>();
        for (Brand brand : brandList) {
            idList.add(brand.getId());
        }
        boolean b = removeByIds(idList);
        if (b) {
            String msg = idList.size() + "个" + BrandTextConstant.NAME + "删除成功";
            List<Object> list = new ArrayList<>(brandList);
            recordService.deleteRecord(msg, list, userId, ip);
            return ResultJson.yes(msg);
        }
        return ResultJson.no(BrandTextConstant.NAME + "删除失败");
    }
}
