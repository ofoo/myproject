package com.guoguo.murongqing.entity.zhongyao;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Category {
    private Long id;
    /**
     * 中药名称
     */
    private String name;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 0正常 1删除
     */
    private Integer delete;

    /**
     * id串
     */
    private List<Long> ids;
    /**
     * 中药列表
     */
    private List<Zhongyao> zhongyaoList;
}
