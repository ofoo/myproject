package com.hongying.lingtianxiang.controller;

import com.hongying.lingtianxiang.entity.Code;
import com.hongying.lingtianxiang.entity.CodeFile;
import com.hongying.lingtianxiang.service.CodeService;
import com.hongying.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;

    /**
     * 创建代码
     * @param explain
     * @param entity
     * @return
     */
    @RequestMapping("/create/code")
    public String createCode(Model model, @RequestParam("explain") String explain, @RequestParam("entity") String entity,@RequestParam("attr") String[] attr) {
        // 处理数据
        List<Map<String,String>> attrList = new ArrayList<>();
        for (String a : attr) {
            String[] arr = a.split("/");
            Map<String,String> map = new HashMap<>();
            map.put("type", StringUtils.capFirst(arr[0]));
            map.put("name",arr[1]);
            map.put("des",arr[2]);
            attrList.add(map);
        }

        // 封装数据
        Code code = new Code();
        code.setExplain(explain);
        code.setEntity(entity);
        code.setAttrList(attrList);

        // 执行操作
        List<CodeFile> codeFileList = codeService.createCode(code);

        // 封装结果
        model.addAttribute("list",codeFileList);

        // 返回页面
        return "code-show";
    }
}
