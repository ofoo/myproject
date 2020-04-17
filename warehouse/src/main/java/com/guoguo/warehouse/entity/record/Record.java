package com.guoguo.warehouse.entity.record;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 记录
 *
 * @author GC
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Record extends Model<Record> {
    /**
     * 记录id
     */
    private Long id;
    /**
     * 记录说明
     */
    @TableField("`explain`")
    private String explain;
    /**
     * 记录数据
     */
    private String data;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户ip地址
     */
    private String ip;
    /**
     * 记录类型
     */
    private String type;
}
