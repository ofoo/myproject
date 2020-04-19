package com.guoguo.config;

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
     * 项目名
     */
    @Value("${system.name}")
    private String sysName;
}
