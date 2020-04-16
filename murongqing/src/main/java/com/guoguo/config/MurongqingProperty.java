package com.guoguo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class MurongqingProperty {

    /**
     * 文件路径
     */
    @Value("${murongqing.file.path}")
    private String filePath;
    /**
     * 域名
     */
    @Value("${murongqing.domain.name}")
    private String domainName;
    /**
     * 默认密码
     */
    @Value("${murongqing.default.password}")
    private String password;
}
