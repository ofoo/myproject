package com.guoguo.warehouse.entity.user;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.guoguo.warehouse.entity.record.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 * @author GC
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<Record> {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户密码
     */
    private String pwd;
    /**
     * 添加时间
     */
    private String addTime;
}
