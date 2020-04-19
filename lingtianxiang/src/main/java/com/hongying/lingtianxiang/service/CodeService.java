package com.hongying.lingtianxiang.service;

import com.hongying.lingtianxiang.entity.Code;
import com.hongying.lingtianxiang.entity.CodeFile;
import com.hongying.util.FreemarkerUtils;
import com.hongying.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeService {

    /**
     * 创建代码
     * @param code
     */
    public List<CodeFile> createCode(Code code){
        List<CodeFile> list = new ArrayList<>();

        String entity = StringUtils.capFirst(code.getEntity());
        String entityCondition = entity+"Condition";
        String entityDao = entity+"Dao";
        String entityBiz=entity+"Biz";
        String entityController=entity+"Controller";
        String entityService=entity+"Service";

        Map root = new HashMap();
        root.put("entity",code.getEntity());
        root.put("explain",code.getExplain());
        root.put("attrList",code.getAttrList());

        CodeFile codeFile = new CodeFile();
        codeFile.setName(entity);
        codeFile.setContent(FreemarkerUtils.createEntity(root));
        codeFile.setType(CodeFile.ENTITY);
        list.add(codeFile);

        codeFile = new CodeFile();
        codeFile.setName(entityCondition);
        codeFile.setContent(FreemarkerUtils.createEntityCondition(root));
        codeFile.setType(CodeFile.ENTITY_CONDITION);
        list.add(codeFile);

        codeFile = new CodeFile();
        codeFile.setName(entityDao);
        codeFile.setContent(FreemarkerUtils.createEntityDao(root));
        codeFile.setType(CodeFile.DAO);
        list.add(codeFile);

        codeFile = new CodeFile();
        codeFile.setName(entityBiz);
        codeFile.setContent(FreemarkerUtils.createEntityBiz(root));
        codeFile.setType(CodeFile.BIZ);
        list.add(codeFile);

        codeFile = new CodeFile();
        codeFile.setName(entityController);
        codeFile.setContent(FreemarkerUtils.createEntityController(root));
        codeFile.setType(CodeFile.CONTROLLER);
        list.add(codeFile);

        codeFile = new CodeFile();
        codeFile.setName(entityService);
        codeFile.setContent(FreemarkerUtils.createEntityService(root));
        codeFile.setType(CodeFile.SERVICE);
        list.add(codeFile);

        return list;
    }
}
