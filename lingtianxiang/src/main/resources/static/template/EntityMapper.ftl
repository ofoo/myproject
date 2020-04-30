<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packName}.dao.${entity?lower_case}.${entity?cap_first}Dao">
    <insert id="add${entity?cap_first}">
        insert into ${table}(<#list fieldList as field>`${field}`,</#list>add_time) values (<#list attrList as attr>#${"{"}${attr.name}${"}"},</#list>now())
    </insert>
    <update id="delete${entity?cap_first}ByIds">
        update ${table} set status=1 where id in
        <foreach collection="list" item="item" open="(" separator="," close=")">
            #${"{item.id}"}
        </foreach>
    </update>
    <update id="update${entity?cap_first}ById">
        update ${table} set <#list updateFieldList as field>${field}<#if (field?has_next)>,</#if></#list> where id=#${"{id}"}
    </update>

    <select id="get${entity?cap_first}List" resultType="${packName}.entity.${entity?lower_case}.${entity?cap_first}">
        select id,<#list fieldList as field>`${field}`,</#list>add_time
        from ${table}
        <where>
            status=0
            <#list selectFieldList as field>
            ${field}
            </#list>
        </where>
        limit #${"{page}"},#${"{limit}"}
    </select>
    <select id="get${entity?cap_first}ListCount" resultType="java.lang.Integer">
        select count(1) from ${table}
        <where>
            status=0
            <#list selectFieldList as field>
            ${field}
            </#list>
        </where>
    </select>
    <select id="get${entity?cap_first}ById" resultType="${packName}.entity.${entity?lower_case}.${entity?cap_first}">
        select
        id,
        <#list fieldList as field>`${field}`<#if (field?has_next)>,</#if></#list>
        from ${table}
        where id=#${"{id}"}
        limit 1
    </select>
</mapper>