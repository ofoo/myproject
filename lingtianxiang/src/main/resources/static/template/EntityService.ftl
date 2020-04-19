package com.base.cn.platform.os.service.${entity?lower_case};

import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first};
import com.base.cn.platform.os.entity.${entity?lower_case}.${entity?cap_first}Condition;
import com.base.cn.platform.os.service.FeignAuthConfig;
import com.base.cn.platform.os.service.manage.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * ${explain}数据接口
 */
@FeignClient(value = BaseService.appName,configuration = FeignAuthConfig.class)
public interface ${entity?cap_first}Service extends BaseService {

    /**
     * ${explain}列表带分页
     * @param condition
     * @param currentPage
     * @return
     */
    @RequestMapping(contextPath + "/get${entity?cap_first}ListByPage")
    PageInfo<${entity?cap_first}> get${entity?cap_first}ListByPage(@RequestBody ${entity?cap_first}Condition condition, @RequestParam("currentPage") int currentPage);

    /**
     *${explain}列表无分页
     * @param condition
     * @return
     */
    @RequestMapping(contextPath + "/get${entity?cap_first}List")
    List<${entity?cap_first}> get${entity?cap_first}List(@RequestBody ${entity?cap_first}Condition condition);

    /**
     * 根据id查询
     * @param condition
     * @return
     */
    @RequestMapping(contextPath + "/get${entity?cap_first}ById")
    ${entity?cap_first} get${entity?cap_first}ById(@RequestBody ${entity?cap_first}Condition condition);

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    @RequestMapping(contextPath + "/get${entity?cap_first}")
    ${entity?cap_first} get${entity?cap_first}(@RequestBody ${entity?cap_first}Condition condition);

    /**
     * 修改${explain}状态
     * @param condition
     * @return
     */
    @RequestMapping(contextPath + "/update${entity?cap_first}StatusByIds")
    Map<String,Object> update${entity?cap_first}StatusByIds(@RequestBody ${entity?cap_first}Condition condition);

    /**
     * 后台保存${explain}基本信息
     * @param ${entity?uncap_first}
     * @return
     */
    @RequestMapping(contextPath + "/save${entity?cap_first}")
    Map<String,Object> save${entity?cap_first}(@RequestBody ${entity?cap_first} ${entity?uncap_first});

    /**
     * 后台保存${explain}基本信息
     * @param ${entity?uncap_first}List
     * @return
     */
    @RequestMapping(contextPath + "/save${entity?cap_first}Batch")
    Map<String,Object> save${entity?cap_first}Batch(@RequestBody List<${entity?cap_first}> ${entity?uncap_first}List);

}
