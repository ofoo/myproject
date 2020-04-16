package com.guoguo.warehouse.controller.goods;

import com.guoguo.warehouse.common.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/warehouse/goods")
public class GoodsController {

    /**
     * 获取页面路径
     *
     * @param pageName
     * @return
     */
    public String getPagePath(String pageName) {
        String pagePackage = "goods/";
        return pagePackage + pageName;
    }

    @RequestMapping("/list")
    public String list() {
        return getPagePath("list");
    }

    @RequestMapping("/add")
    public String add() {
        return getPagePath("save");
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable Long id) {
        return getPagePath("save");
    }

    @RequestMapping("/ajax/list")
    @ResponseBody
    public ResultJson ajaxList() {
        return ResultJson.yes("操作成功");
    }

    @RequestMapping("/ajax/save")
    @ResponseBody
    public ResultJson ajaxSave() {
        return ResultJson.yes("操作成功");
    }

    @RequestMapping("/ajax/delete")
    @ResponseBody
    public ResultJson ajaxDelete() {
        return ResultJson.yes("操作成功");
    }
}
