package com.guoguo.murongqing.controller.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguo.config.MurongqingProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    /**
     * 无权限页面
     */
    protected static final String NO_ACCESS = "common/no-access";
    /**
     * 用户未登录页面
     */
    protected static final String NO_LOGIN = "user/admin-user-login";

    @Autowired
    private MurongqingProperty murongqingProperty;

    /**
     * 重定向
     * @param path
     * @return
     */
    protected String redirectPage(String path) {
        return "redirect:" + murongqingProperty.getDomainName() + path;
    }
}
