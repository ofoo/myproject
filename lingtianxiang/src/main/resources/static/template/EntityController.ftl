package ${packName}.controller.${entity?lower_case};

import ${packName}.entity.${entity?lower_case}.${entity?cap_first};
import ${packName}.service.${entity?lower_case}.${entity?cap_first}Service;
import ${packName}.constant.${entity?lower_case}.${entity?cap_first}Constant;
import ${packName}.common.DataJson;
import ${packName}.common.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ${explain}
 */
@Controller
@RequestMapping("/ynz/${entity?lower_case}")
public class ${entity?cap_first}Controller {

    @Autowired
    private ${entity?cap_first}Service ${entity?lower_case}Service;

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "${entity?lower_case}/";
        return pagePackage + pageName;
    }

    /**
     * 列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.setAttribute("listPageTitle", ${entity?cap_first}Constant.ADD_PAGE_TITLE);
        request.setAttribute("addPageTitle", ${entity?cap_first}Constant.ADD_PAGE_TITLE);
        request.setAttribute("updatePageTitle", ${entity?cap_first}Constant.ADD_PAGE_TITLE);
        return getPagePath("list");
    }

    /**
     * 分页查询${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    @RequestMapping("/ajax/list")
    @ResponseBody
    public DataJson ajaxList(${entity?cap_first} ${entity?lower_case}) {
        return ${entity?lower_case}Service.get${entity?cap_first}List(${entity?lower_case});
    }

    /**
     * 添加页面
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return getPagePath("save");
    }

    /**
     * 修改页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request) {
        ${entity?cap_first} ${entity?lower_case} = ${entity?lower_case}Service.get${entity?cap_first}ById(id);
        request.setAttribute("${entity?lower_case}", ${entity?lower_case});
        return getPagePath("save");
    }

    /**
     * 保存${explain}
     *
     * @param ${entity?lower_case}
     * @return
     */
    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave(@RequestBody ${entity?cap_first} ${entity?lower_case}) {
        return ${entity?lower_case}Service.save${entity?cap_first}(${entity?lower_case});
    }

    /**
     * 删除${explain}
     *
     * @param ${entity?lower_case}List
     * @return
     */
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete(@RequestBody List<${entity?cap_first}> ${entity?lower_case}List) {
        return ${entity?lower_case}Service.delete${entity?cap_first}ByIds(${entity?lower_case}List);
    }
}
