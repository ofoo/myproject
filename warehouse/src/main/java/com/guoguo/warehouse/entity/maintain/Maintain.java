package com.guoguo.warehouse.entity.maintain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 维修
 *
 * @author GC
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Maintain extends Model<Maintain> {
    /**
     * 维修id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 维修价格
     */
    private String sellingPrice;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 客户住址
     */
    private String clientAddress;
    /**
     * 客户手机
     */
    private String clientPhone;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 维修状态 0未完成 1完成
     */
    private Integer status;
    /**
     * 添加时间
     */
    private String addTime;
}
