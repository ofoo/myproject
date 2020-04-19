package com.base.cn.platform.os.controller.${entity?lower_case};

import com.base.cn.platform.os.common.controller.BaseController;
import com.base.cn.platform.os.common.mybatis.Pagination;
import com.base.cn.platform.os.common.utils.result.ResultUtil;
import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first};
import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first}Condition;
import com.base.cn.platform.os.service.${entity?lower_case}.${entity?cap_first}Biz;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * ${explain}controller
 */
@RestController
@RequestMapping("/manage")
public class ${entity?cap_first}Controller extends BaseController {

    @Autowired
    private ${entity?cap_first}Biz ${entity?uncap_first}Biz;

    /**
     * 查询${explain}列表带分页
     * @param condition
     * @param currentPage
     * @return
     */
    @RequestMapping("/get${entity?cap_first}ListByPage")
    public PageInfo<${entity?cap_first}> get${entity?cap_first}ListByPage(@RequestBody ${entity?cap_first}Condition condition, @RequestParam("currentPage") int currentPage){
        Pagination page = new Pagination();
        page.setCurrentPage(currentPage);
        PageInfo<${entity?cap_first}> ${entity?uncap_first}PageInfo = ${entity?uncap_first}Biz.get${entity?cap_first}ListByPage(condition,page);
        return ${entity?uncap_first}PageInfo;
    }

    /**
     * 查询${explain}列表无分页
     * @param condition
     * @return
     */
    @RequestMapping("/get${entity?cap_first}List")
    public List<${entity?cap_first}> get${entity?cap_first}List(@RequestBody ${entity?cap_first}Condition condition){
        List<${entity?cap_first}> ${entity?uncap_first}List = ${entity?uncap_first}Biz.get${entity?cap_first}List(condition);
        return ${entity?uncap_first}List;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/get${entity?cap_first}ById")
    public ${entity?cap_first} get${entity?cap_first}ById(@RequestParam("id") BigDecimal id){
        return ${entity?uncap_first}Biz.get${entity?cap_first}ById(id);
    }

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    @RequestMapping("/get${entity?cap_first}")
    public ${entity?cap_first} get${entity?cap_first}(@RequestBody ${entity?cap_first}Condition condition){
        return ${entity?uncap_first}Biz.get${entity?cap_first}(condition);
    }

    /**
     * 修改${explain}状态
     * @param condition
     * @return
     */
    @RequestMapping("/updateStatusByIds")
    public Map<String,Object> updateStatusByIds(@RequestBody ${entity?cap_first}Condition condition){
        ${entity?uncap_first}Biz.updateStatusByIds(condition);
        return ResultUtil.SUCCESS("${explain}状态修改成功");
    }

    /**
     * 保存${explain}
     * @param ${entity?uncap_first}
     * @return
     */
    @RequestMapping("/save${entity?cap_first}")
    public Map<String,Object> save${entity?cap_first}(@RequestBody ${entity?cap_first} ${entity?uncap_first}){
        ${entity?uncap_first}Biz.save${entity?cap_first}(${entity?uncap_first});
        return ResultUtil.SUCCESS("保存成功",${entity?uncap_first});
    }

    /**
     * 批量保存${explain}
     * @param ${entity?uncap_first}List
     * @return
     */
    @RequestMapping("/save${entity?cap_first}Batch")
    public Map<String,Object> save${entity?cap_first}Batch(@RequestBody List<${entity?cap_first}> ${entity?uncap_first}List){
        ${entity?uncap_first}Biz.batchSave(${entity?uncap_first}List);
        return ResultUtil.SUCCESS("保存成功",${entity?uncap_first}List);
    }
}
