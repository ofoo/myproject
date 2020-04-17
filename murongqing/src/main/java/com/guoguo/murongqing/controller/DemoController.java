package com.guoguo.murongqing.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DemoController {
    @RequestMapping("/read/excel")
    public String readExcel(MultipartFile file, Model model) {
        try {
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<List<Object>> read = reader.read();
            for (int i = 0; i < read.size(); i++) {
                for (Object o : read.get(i)) {
                    System.out.print(o.toString());
                    System.out.print(" ");
                }
                System.out.println();
            }
            model.addAttribute("msg", "导入成功");
        } catch (Exception e) {
            log.error("readExcel", e);
            model.addAttribute("msg", "导入失败");
        }
        return "msg";
    }

    @RequestMapping("/write/excel")
    public String writerExcel(Model model, HttpServletRequest request, HttpServletResponse response) {
        ExcelWriter writer = null;
        ServletOutputStream out = null;
        try {
            /*List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
            List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
            List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
            List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
            List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

            List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);*/

            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("姓名", "张三");
            row1.put("年龄", 23);
            row1.put("成绩", 88.32);
            row1.put("是否合格", true);
            row1.put("考试日期", DateUtil.date());

            Map<String, Object> row2 = new LinkedHashMap<>();
            row2.put("姓名", "李四");
            row2.put("年龄", 33);
            row2.put("成绩", 59.50);
            row2.put("是否合格", false);
            row2.put("考试日期", DateUtil.date());

            ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);

            // 通过工具类创建writer，默认创建xls格式
            writer = ExcelUtil.getWriter();
            //合并单元格后的标题行，使用默认标题样式
//            writer.merge(row1.size() - 1, "测试标题");
            // 一次性写出内容，使用默认样式，强制输出标题
            writer.write(rows, true);

            //response为HttpServletResponse对象
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            response.setHeader("Content-Disposition", "attachment;filename=美女.xls");
            //out为OutputStream，需要写出到的目标流
            out = response.getOutputStream();

            writer.flush(out, true);
            model.addAttribute("msg", "导出成功");
        } catch (Exception e) {
            log.error("writerExcel", e);
            model.addAttribute("msg", "导出失败");
        } finally {
            // 关闭writer，释放内存
            if (writer != null) {
                writer.close();
            }
            //此处记得关闭输出Servlet流
            if (out != null) {
                IoUtil.close(out);
            }
        }
        return "msg";
    }

    @RequestMapping("/file")
    public String file() {
        return "file";
    }
}
