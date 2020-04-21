package ${packName}.dao.${entity?lower_case};

import ${packName}.entity.${entity?lower_case}.${entity?cap_first};
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${explain}
 */
public interface ${entity?cap_first}Dao {

    /**
     * 查询所有${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    List<${entity?cap_first}> get${entity?cap_first}List(${entity?cap_first} ${entity?lower_case});

    /**
     * 按id查询${explain}
     *
     * @param id
     * @return
     */
    ${entity?cap_first} get${entity?cap_first}ById(@Param("id") Long id);

    /**
     * 按id删除${explain}
     *
     * @param ${entity?lower_case}List
     * @return
     */
    int delete${entity?cap_first}ByIds(List<${entity?cap_first}> ${entity?lower_case}List);

    /**
     * 根据id修改${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    int update${entity?cap_first}ById(${entity?cap_first} ${entity?lower_case});

    /**
     * 添加${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    int add${entity?cap_first}(${entity?cap_first} ${entity?lower_case});

    /**
     * 查询${explain}总数
     *
     * @param ${entity?lower_case}
     * @return
     */
    Integer get${entity?cap_first}ListCount(${entity?cap_first} ${entity?lower_case});
}
