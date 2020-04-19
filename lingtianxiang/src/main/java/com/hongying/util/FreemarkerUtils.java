package com.hongying.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.ClassUtils;

import java.io.StringWriter;
import java.util.Map;

public class FreemarkerUtils {
    private static Configuration cfg;
    private static String rootDir;

    static {
        rootDir = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static";
        cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(FreemarkerUtils.class,"/static/template");
        cfg.setDefaultEncoding("UTF-8");
    }

    public final static String ENTITY = "Entity.ftl";
    public final static String ENTITY_CONDITION = "EntityCondition.ftl";
    public final static String ENTITY_DAO = "EntityDao.ftl";
    public final static String ENTITY_BIZ = "EntityBiz.ftl";
    public final static String ENTITY_CONTROLLER = "EntityController.ftl";
    public final static String ENTITY_SERVICE = "EntityService.ftl";

    private static String create(Map root, String templateName) {
        String str = "";
        try {
            Template temp = cfg.getTemplate(templateName);
            StringWriter stringWriter = new StringWriter();
            temp.process(root, stringWriter);
            str = stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String createEntity(Map root) {
        return create(root, ENTITY);
    }

    public static String createEntityCondition(Map root) {
        return create(root, ENTITY_CONDITION);
    }

    public static String createEntityDao(Map root) {
        return create(root, ENTITY_DAO);
    }

    public static String createEntityBiz(Map root) {
        return create(root, ENTITY_BIZ);
    }

    public static String createEntityController(Map root) {
        return create(root, ENTITY_CONTROLLER);
    }

    public static String createEntityService(Map root) {
        return create(root, ENTITY_SERVICE);
    }

    public static void main(String[] args) {
        System.out.println(FreemarkerUtils.class.getClassLoader().getResource("static/template"));
    }
}
