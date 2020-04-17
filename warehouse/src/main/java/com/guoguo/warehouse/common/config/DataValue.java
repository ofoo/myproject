package com.guoguo.warehouse.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 数据值
 * @author GC
 */
@Component
@Data
public class DataValue {
    /**
     * 登录账号
     */
    @Value("${system.login.name}")
    private String loginName = "admin";
    /**
     * 登录密码
     */
    @Value("${system.login.pwd}")
    private String loginPwd = "123";
    /**
     * 项目域名
     */
    @Value("${system.domain.name}")
    private String domainName;
    /**
     * 项目域名
     */
    @Value("${system.name}")
    private String sysName;
}
