package com.guoguo.lingtianxiang.controller;

import com.guoguo.lingtianxiang.entity.Code;
import com.guoguo.lingtianxiang.entity.CodeFile;
import com.guoguo.lingtianxiang.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;

    /**
     * 默认包名称
     */
    @Value("${system.pack.name}")
    private String packName;
    /**
     * code文件路径
     */
    @Value("${system.code.file.path}")
    private String codeFilePath;
    /**
     * mapper文件路径
     */
    @Value("${system.mapper.file.path}")
    private String mapperFilePath;
    /**
     * page文件路径
     */
    @Value("${system.page.file.path}")
    private String pageFilePath;
    /**
     * 请求根路径
     */
    @Value("${system.request.root.url}")
    private String requestRootUrl;

    /**
     * 生成代码页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("packName", packName);
        model.addAttribute("codeFilePath", codeFilePath);
        model.addAttribute("mapperFilePath", mapperFilePath);
        model.addAttribute("pageFilePath", pageFilePath);
        model.addAttribute("requestRootUrl", requestRootUrl);
        return "index";
    }

    /**
     * 创建代码
     *
     * @param model
     * @param code
     * @return
     */
    @RequestMapping("/create/code")
    public String createCode(Model model, Code code) {
        // 执行操作
        List<CodeFile> codeFileList = codeService.createCode(code);
        // 封装结果
        model.addAttribute("list", codeFileList);
        // 返回页面
        return "code-show";
    }

}
