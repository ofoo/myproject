package com.guoguo.warehouse.entity.goods.sell.record;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品售卖记录
 *
 * @author GC
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSellRecord extends Model<GoodsSellRecord> {
    /**
     * 商品售卖记录id
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
     * 价格
     */
    private String price;
    /**
     * 添加时间
     */
    private String addTime;
}
