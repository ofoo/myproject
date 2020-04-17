package com.guoguo.murongqing.entity.zhongyao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Zhongyao {
    private Long id;
    /**
     * 中药名称
     */
    private String name;
    /**
     * 行号
     */
    private String x;
    /**
     * 列号
     */
    private String y;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 0正常 1删除
     */
    private Integer delete;
    /**
     * 图片
     */
    private String image;
    /**
     * 价格
     */
    private String price;
    /**
     * 重量
     */
    private String weight;
    /**
     * 类别id
     */
    private Long categoryId;
    /**
     * 剂量
     */
    private String dosage;

    /**
     * id串
     */
    private List<Long> ids;
    /**
     * 中药类别
     */
    private Category category;
}
