package com.base.cn.platform.os.entity.${entity?lower_case};

import com.base.cn.platform.os.common.mybatis.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * ${explain}实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entity?cap_first} extends IEntity {
<#list attrList as attr>
    /**
     * ${attr.des}
     */
    private ${attr.type} ${attr.name};
</#list>
}
