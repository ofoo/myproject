package com.guoguo.warehouse.controller.record;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guoguo.warehouse.common.DataJson;
import com.guoguo.warehouse.entity.goods.Goods;
import com.guoguo.warehouse.entity.record.Record;
import com.guoguo.warehouse.service.record.RecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 记录管理
 * @author GC
 */
@Controller
@RequestMapping("/warehouse/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "record/";
        return pagePackage + pageName;
    }

    /**
     * 列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return getPagePath("list");
    }

    /**
     * 数据列表
     * @param record
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(Record record, Integer page, Integer limit) {
        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(record.getExplain()), Record::getExplain, record.getExplain());
        return recordService.getListPage(lambdaQueryWrapper, page, limit);
    }
}
