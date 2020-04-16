package com.guoguo.warehouse.entity.brand;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌
 *
 * @author GC
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Brand extends Model<Brand> {
    /**
     * 品牌id
     */
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 用户id
     */
    private Long userId;
}
