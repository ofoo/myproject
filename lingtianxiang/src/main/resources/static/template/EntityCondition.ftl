package com.base.cn.platform.os.entity.${entity?lower_case};

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${explain}实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entity?cap_first}Condition extends ${entity?cap_first} {
    /**
     * id串
     */
    private String ids;
}
