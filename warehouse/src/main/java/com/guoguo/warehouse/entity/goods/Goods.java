package com.guoguo.warehouse.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品
 *
 * @author GC
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods extends Model<Goods> {
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 原价
     */
    private String price;
    /**
     * 商品数量
     */
    private String quantity;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 上货数量
     */
    private String upGoodsQuantity;
    /**
     * 售价
     */
    private String sellingPrice;
    /**
     * 品牌id
     */
    private String brandName;
}
