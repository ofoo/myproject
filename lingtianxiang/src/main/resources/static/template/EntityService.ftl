package ${packName}.service.${entity?lower_case};

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import ${packName}.dao.${entity?lower_case}.${entity?cap_first}Dao;
import ${packName}.entity.${entity?lower_case}.${entity?cap_first};
import ${packName}.constant.${entity?lower_case}.${entity?cap_first}Constant;
import ${packName}.common.DataJson;
import ${packName}.common.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${explain}
 */
@Service
public class ${entity?cap_first}Service {

    @Autowired
    private ${entity?cap_first}Dao ${entity?lower_case}Dao;

    /**
     * 按id查询${explain}
     *
     * @param id
     * @return
     */
    public ${entity?cap_first} get${entity?cap_first}ById(Long id) {
        return ${entity?lower_case}Dao.get${entity?cap_first}ById(id);
    }

    /**
     * 按id删除${explain}
     *
     * @param ${entity?lower_case}List
     * @return
     */
    public ResultJson delete${entity?cap_first}ByIds(List<${entity?cap_first}> ${entity?lower_case}List) {
        int rows = ${entity?lower_case}Dao.delete${entity?cap_first}ByIds(${entity?lower_case}List);
        if (rows > 0) {
            return ResultJson.yes(${entity?cap_first}Constant.YES);
        }
        return ResultJson.no(${entity?cap_first}Constant.NO);
    }

    /**
     * 保存${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    public ResultJson save${entity?cap_first}(${entity?cap_first} ${entity?lower_case}) {
        if (ObjectUtil.isNotNull(${entity?lower_case}.getId())) {
            // 修改${explain}
            int rows = ${entity?lower_case}Dao.update${entity?cap_first}ById(${entity?lower_case});
            if (rows > 0) {
                return ResultJson.yes(${entity?cap_first}Constant.YES);
            }
        } else {
            // 添加${explain}
            int rows = ${entity?lower_case}Dao.add${entity?cap_first}(${entity?lower_case});
            if (rows > 0) {
                return ResultJson.yes(${entity?cap_first}Constant.YES);
            }
        }
        return ResultJson.no(${entity?cap_first}Constant.NO);
    }

    /**
     * 分页查询${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    public DataJson get${entity?cap_first}List(${entity?cap_first} ${entity?lower_case}) {
        // 查询分页页数
        int[] ints = PageUtil.transToStartEnd(${entity?lower_case}.getPage() - 1, ${entity?lower_case}.getLimit());
        ${entity?lower_case}.setPage(ints[0]);
        ${entity?lower_case}.setLimit(ints[1]);
        List<${entity?cap_first}> list = ${entity?lower_case}Dao.get${entity?cap_first}List(${entity?lower_case});
        // 查询总页数
        Integer totalCount = ${entity?lower_case}Dao.get${entity?cap_first}ListCount(${entity?lower_case});
        int totalPage = PageUtil.totalPage(totalCount, ${entity?lower_case}.getLimit());

        return DataJson.list(totalPage, list);
    }
}
