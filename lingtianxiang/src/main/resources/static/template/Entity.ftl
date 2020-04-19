package ${packName}.entity.${entity?lower_case};

import lombok.Data;

import java.util.Date;
<#list attrTypeList as type>
    ${type.name}
<#else>

</#list>
/**
 * ${explain}
 */
@Data
public class ${entity?cap_first} {
    private Long id;
<#list attrList as attr>
    /**
     * ${attr.desc}
     */
    private ${attr.type} ${attr.name};
</#list>
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 用户状态 0正常 2删除
     */
    private Integer status;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 数据条数
     */
    private Integer limit;
}
