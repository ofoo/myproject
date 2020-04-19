package com.guoguo.lingtianxiang.service;

import com.guoguo.lingtianxiang.entity.Attr;
import com.guoguo.lingtianxiang.entity.Code;
import com.guoguo.lingtianxiang.entity.CodeFile;
import com.guoguo.util.FreemarkerUtils;
import com.guoguo.util.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeService {

    /**
     * 创建代码
     *
     * @param code
     */
    public List<CodeFile> createCode(Code code) {
        // 属性集合
        List<Attr> attrList = new ArrayList<>();
        // 属性类型集合
        List<String> attrTypeList = new ArrayList<>();
        // 表字段集合
        List<String> fieldList = new ArrayList<>();
        // 表修改SQL字段集合
        List<String> updateFieldList = new ArrayList<>();
        // 表分页查询SQL条件集合
        List<String> selectFieldList = new ArrayList<>();

        for (String s : code.getAttr()) {
            String[] array = StringUtils.strTurnStringArray(s, StringUtils.SYMBOL);

            // 获取类属性值
            Attr attr = new Attr();
            attr.setType(array[0]);
            attr.setName(array[1]);
            attr.setDesc(array[2]);
            attrList.add(attr);

            // 获取属性类型值
            String attrType = getAttrType(array[0]);
            if (attrType != null) {
                attrTypeList.add(attrType);
            }

            // 获取表字段值
            fieldList.add(StringUtils.toUnderlineCase(array[1]));

            updateFieldList.add(StringUtils.toUnderlineCase(array[1]) + "=" + "#{"+array[1]+"}");

            StringBuffer where = new StringBuffer();
            // 获取表修改SQL字段
            if (array[0].equals("String")) {
                where.append("<if test=\"" + array[1] + "!=null and " + array[1] + "!=''\">");
                where.append("and `" + StringUtils.toUnderlineCase(array[1]) + "` like CONCAT('%',#{" + array[1] + "},'%')");
                where.append("</if>");
            } else {
                where.append("<if test=\"" + array[1] + "!=null\">");
                where.append("and `" + StringUtils.toUnderlineCase(array[1]) + "`=#{" + array[1] + "}");
                where.append("</if>");
            }
            selectFieldList.add(where.toString());
        }

        // 组装类名称
        String entity = StringUtils.capFirst(code.getEntity());
        String entityDao = entity + CodeFile.DAO;
        String entityService = entity + CodeFile.SERVICE;
        String entityController = entity + CodeFile.CONTROLLER;
        String entityConstant = entity + CodeFile.CONSTANT;
        String entityMapper = entity + CodeFile.MAPPER;
        String entityList = CodeFile.LIST;
        String entitySave = CodeFile.SAVE;

        // 封装模板使用到的值
        Map root = new HashMap();
        root.put("packName", code.getPackName());
        root.put("explain", code.getExplain());
        root.put("entity", code.getEntity());
        root.put("attrList", attrList);
        root.put("attrTypeList", attrTypeList);
        root.put("table", code.getTable());
        root.put("fieldList", fieldList);
        root.put("updateFieldList", updateFieldList);
        root.put("selectFieldList", selectFieldList);
        root.put("requestRootUrl", code.getRequestRootUrl());

        List<CodeFile> list = new ArrayList<>();

        // 生成entity代码
        File file = new File(code.getCodeFilePath() + "\\entity\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entity + ".java";
        CodeFile codeFile = new CodeFile();
        codeFile.setName(entity + ".java");
        codeFile.setContent(FreemarkerUtils.createEntity(root));
        codeFile.setType(CodeFile.ENTITY);
        list.add(codeFile);

        // 生成constant代码
        file = new File(code.getCodeFilePath() + "\\tool\\constant");
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entityConstant + ".java";
        codeFile = new CodeFile();
        codeFile.setName(entityConstant + ".java");
        codeFile.setContent(FreemarkerUtils.createEntityConstant(root));
        codeFile.setType(CodeFile.CONSTANT);
        list.add(codeFile);

        // 生成dao代码
        file = new File(code.getCodeFilePath() + "\\dao\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entityDao + ".java";
        codeFile = new CodeFile();
        codeFile.setName(entityDao + ".java");
        codeFile.setContent(FreemarkerUtils.createEntityDao(root));
        codeFile.setType(CodeFile.DAO);
        list.add(codeFile);

        // 生成service代码
        file = new File(code.getCodeFilePath() + "\\service\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entityService + ".java";
        codeFile = new CodeFile();
        codeFile.setName(entityService + ".java");
        codeFile.setContent(FreemarkerUtils.createEntityService(root));
        codeFile.setType(CodeFile.SERVICE);
        list.add(codeFile);

        // 生成controller代码
        file = new File(code.getCodeFilePath() + "\\controller\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entityController + ".java";
        codeFile = new CodeFile();
        codeFile.setName(entityController + ".java");
        codeFile.setContent(FreemarkerUtils.createEntityController(root));
        codeFile.setType(CodeFile.CONTROLLER);
        list.add(codeFile);

        // 生成mapper代码
        file = new File(code.getMapperFilePath() + "\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entityMapper + ".xml";
        codeFile = new CodeFile();
        codeFile.setName(entityMapper + ".xml");
        codeFile.setContent(FreemarkerUtils.createEntityMapper(root));
        codeFile.setType(CodeFile.MAPPER);
        list.add(codeFile);

        // 生成list.html代码
        file = new File(code.getPageFilePath() + "\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entityList + ".html";
        codeFile = new CodeFile();
        codeFile.setName(entityList + ".html");
        codeFile.setContent(FreemarkerUtils.createPageList(root));
        codeFile.setType(CodeFile.LIST);
        list.add(codeFile);

        // 生成save.html代码
        file = new File(code.getPageFilePath() + "\\" + entity.toLowerCase());
        if (!file.exists()) {
            file.mkdirs();
        }
        FreemarkerUtils.filePath = file.getPath() + "\\" + entitySave + ".html";
        codeFile = new CodeFile();
        codeFile.setName(entitySave + ".html");
        codeFile.setContent(FreemarkerUtils.createPageSave(root));
        codeFile.setType(CodeFile.SAVE);
        list.add(codeFile);

        return list;
    }

    /**
     * 获取属性类型
     *
     * @param s
     */
    private String getAttrType(String s) {
        String type = null;
        switch (s) {
            case "Date":
                type = "import java.util.Date;";
                break;
            case "BigDecimal":
                type = "import java.math.BigDecimal;";
                break;
        }
        return type;
    }
}
